from deepface import DeepFace
import pandas as pd
import os
import cv2
import requests

models = [
    "VGG-Face",
    "Facenet",
    "Facenet512",
    "OpenFace",
    "DeepFace",
    "DeepID",
    "ArcFace",
    "Dlib",
    "SFace",
    "GhostFaceNet",
]

metrics = ["cosine", "euclidean", "euclidean_l2"]

# S3 기본 URL
s3_base_url = 'https://discipline-actor.s3.ap-northeast-2.amazonaws.com'

def process_image(input_img_path, images_folder_path, s3_base_url):
    input_img = cv2.imread(input_img_path)
    file_name = os.path.basename(input_img_path)
    
    # 로그 추가
    print(f"Input image path: {input_img_path}")
    print(f"File name: {file_name}")

    df = DeepFace.find(img_path=input_img, db_path=images_folder_path, detector_backend='retinaface', model_name='ArcFace', enforce_detection=False)

    if isinstance(df, list):
        df = df[0]  # 리스트의 첫 번째 요소를 데이터프레임으로 사용

    # 'identity' 컬럼을 'imagePath'로 변환
    if 'identity' in df.columns:
        df.rename(columns={'identity': 'imagePath'}, inplace=True)

    # 'imagePath'와 'distance'가 없는 경우 초기화
    if 'imagePath' not in df.columns:
        df['imagePath'] = None
    if 'distance' not in df.columns:
        df['distance'] = 0.0

    # URL 추가
    df['url'] = df['imagePath'].apply(lambda x: f"{s3_base_url}/{os.path.basename(x)}" if x else None)

    # 입력 이미지의 파일명 추가
    df['inputImage'] = file_name
    
    # 자기 자신을 필터링
    df = df[df['imagePath'].apply(lambda x: os.path.basename(x) != file_name)]

    # 데이터프레임을 리스트로 변환
    df_list = df[['imagePath', 'distance', 'url', 'inputImage']].to_dict(orient='records')

    # Spring Boot 애플리케이션의 API 엔드포인트 URL
    url = 'https://cine-hub-5cab44f98028.herokuapp.com/api/recommendation'

    # JSON 리스트 전송
    response = requests.post(url, json=df_list, headers={'Content-Type': 'application/json'})

    # 응답 출력
    print(response.text)

    return df

