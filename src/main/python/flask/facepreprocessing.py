from deepface import DeepFace
import matplotlib.pyplot as plt
import os
import cv2

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


def process_image(input_img_path, images_folder_path):
    input_img = cv2.imread(input_img_path)

    df = DeepFace.find(img_path=input_img, db_path=images_folder_path, detector_backend='retinaface', model_name='ArcFace', enforce_detection=False)
    print(df)

    return df
