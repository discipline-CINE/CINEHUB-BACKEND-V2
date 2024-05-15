from flask import Flask, request
from celery import Celery
from celery.schedules import crontab
import matplotlib.pyplot as plt
from facepreprocessing import process_image
import json
from celery_worker import make_celery
import boto3
import os

# AWS 인증 정보 설정
aws_access_key_id = 'AKIAVEEOCLJLPMGCM2FB'
aws_secret_access_key = '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'
region_name = 'ap-northeast-2'
bucket_name = 'discipline-s3'

# S3 서비스 리소스 생성
s3_client = boto3.client(
    's3',
    aws_access_key_id=aws_access_key_id,
    aws_secret_access_key=aws_secret_access_key,
    region_name=region_name
)

app = Flask(__name__)
app.config.update(
    broker_url='redis://localhost:6379/0',
    result_backend='redis://localhost:6379/0',
    beat_schedule={
        'process-images-every-hour': {
            'task': 'download_and_process_image_task',
            'schedule': crontab(minute=40, hour='*'),
        },
    },
)


celery = make_celery(app)
current_file_path = os.path.abspath(__file__)
project_folder = os.path.dirname(os.path.dirname(current_file_path))  # app.py의 상위 폴더 (python 폴더)
temp_folder = os.path.join(project_folder, '..', 'resources', 'images', 'actor')

@app.route('/')
def home():
    return "home"

# Springboot 메인 서버에서 json 데이터 받아옴
@app.route('/receive_string', methods=['POST'])
def receive_string():
    dto_json = request.get_json()
    
    # dto_json을 response에 저장
    response = json.dumps(dto_json, ensure_ascii=False)
    
    print(dto_json)
    
    return response

@app.route('/process_image', methods=['GET'])
def download_and_process_images():
    # S3 버킷에서 이미지 목록 가져오기
    response = s3_client.list_objects_v2(Bucket=bucket_name)
    os.makedirs(temp_folder, exist_ok=True) # 경로가 없다면 생성
    
    if 'Contents' in response:
        for obj in response['Contents']:
            file_name = obj['Key']
            local_file_path = os.path.join(temp_folder, file_name)
            
            # 파일이 로컬에 없으면 S3 버킷에서 다운로드
            if not os.path.exists(local_file_path):
                s3_client.download_file(bucket_name, file_name, local_file_path)
                print(f"Downloaded {file_name} from S3 bucket to {local_file_path}")
    
    # 로컬에 저장된 모든 이미지에 대해 얼굴 인식 처리 수행
    for file_name in os.listdir(temp_folder):
        local_file_path = os.path.join(temp_folder, file_name)
        if os.path.isfile(local_file_path) and local_file_path.lower().endswith(('.png', '.jpg', '.jpeg')):
            print(f"Processing {local_file_path}...")
            process_image(local_file_path, temp_folder)
    
    return {"status": "Completed processing all images"}

@celery.task(name='download_and_process_image_task')
def download_and_process_images_task():
    # S3 버킷에서 이미지 목록 가져오기
    response = s3_client.list_objects_v2(Bucket=bucket_name)
    os.makedirs(temp_folder, exist_ok=True) # 경로가 없다면 생성
    
    if 'Contents' in response:
        for obj in response['Contents']:
            file_name = obj['Key']
            local_file_path = os.path.join(temp_folder, file_name)
            
            # 파일이 로컬에 없으면 S3 버킷에서 다운로드
            if not os.path.exists(local_file_path):
                s3_client.download_file(bucket_name, file_name, local_file_path)
                print(f"Downloaded {file_name} from S3 bucket to {local_file_path}")
    
    # 로컬에 저장된 모든 이미지에 대해 얼굴 인식 처리 수행
    for file_name in os.listdir(temp_folder):
        local_file_path = os.path.join(temp_folder, file_name)
        if os.path.isfile(local_file_path) and local_file_path.lower().endswith(('.png', '.jpg', '.jpeg')):
            print(f"Processing {local_file_path}...")
            process_image(local_file_path, temp_folder)
    
    return {"status": "Completed processing all images"}

if __name__ == '__main__':
    app.run(debug=True)