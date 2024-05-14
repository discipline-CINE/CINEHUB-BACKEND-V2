from flask import Flask, request, render_template
from deepface import DeepFace
from werkzeug.utils import secure_filename
from socket import *
from celery import current_task
from celery.schedules import crontab
from celery_worker import make_celery
import boto3
import json
import numpy as np
import pandas as pd
import os

app = Flask(__name__)

# celery 설정, 매 정각마다 데이터 갱신
app.config.update(
    CELERY_BROKER_URL='redis://localhost:6379/0',
    CELERY_RESULT_BACKEND='redis://localhost:6379/0',
    CELERY_BEAT_SCHEDULE={
        'process-images-every-hour': {
            'task': 'process_images',
            'schedule': crontab(minute=0, hour='*'),
        },
    },
)

celery = make_celery(app)

s3_resource = boto3.resource('s3',
        aws_access_key_id='AKIAVEEOCLJLPMGCM2FB',
        aws_secret_access_key='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',
        region_name='ap-northeast-2')

bucket_name = 'discipline-s3'

# 디버그용 옵션
pd.set_option('display.max_colwidth', None)

# 나중에 경로 변경
BASE_DIR = "D:\\webpr\\CINEHUB-BACKEND-V2\\src\\main\\python\\face"
temp_folder = os.path.join(BASE_DIR, "temp")
results_csv_path = os.path.join(BASE_DIR, "results.csv")  # 결과를 저장할 CSV 파일 경로

def process_image(image_path):
    try:
        # 이미지 경로를 이용해 DeepFace 로직 적용
        result_data = DeepFace.find(img_path=image_path, db_path=temp_folder, detector_backend='retinaface', model_name='ArcFace', enforce_detection=False)
        print(result_data)

        # DataFrame 이외의 타입이 반환될 경우, DataFrame으로 변환
        if isinstance(result_data, list):
            # 빈 DataFrame 생성 또는 list를 DataFrame으로 변환
            df = pd.DataFrame(result_data)
        else:
            df = result_data
        # 결과를 CSV 파일에 추가
        df.to_csv(results_csv_path, mode='a', header=not os.path.exists(results_csv_path))
    except Exception as e:
        with open(results_csv_path, "a") as result_file:
            result_file.write(f"Error processing {image_path}: {e}\n\n")

@app.route('/process_images', methods=['GET'])
@celery.task(name='process_images')
def process_images():
    # S3 버킷의 모든 객체를 반복하여 처리
    bucket = s3_resource.Bucket(bucket_name)
    for obj in bucket.objects.all():
        if obj.key.endswith(('.jpg', '.png', '.jpeg')):
            local_image_path = os.path.join(temp_folder, obj.key)
            os.makedirs(os.path.dirname(local_image_path), exist_ok=True)
            # 이미지가 로컬에 이미 존재하는지 확인
            if not os.path.exists(local_image_path):
                # 로컬에 이미지가 없으면 S3 버킷에서 다운로드
                bucket.download_file(obj.key, local_image_path)
            
            # 이미지 처리
            process_image(local_image_path)

    return {"status": "All images processed"}

# Springboot 메인 서버에서 json 데이터 받아옴
@app.route('/receive_string', methods=['POST'])
def receive_string():
    dto_json = request.get_json()
    
    # dto_json을 response에 저장
    response = json.dumps(dto_json, ensure_ascii=False)
    
    print(dto_json)
    
    return response

if __name__ == "__main__":
    app.run('0.0.0.0', port=5000, debug=True)