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

# 추후에 DB에 맞게 설정 변경 - 현재 로컬 데이터베이스
current_path = os.path.dirname(os.path.realpath(__file__))
images_folder_path = os.path.join(current_path, '../../resources/images/face')
input_img_path = os.path.join(images_folder_path, '../img4.jpg')
input_img = cv2.imread(input_img_path)

df = DeepFace.find(img_path=input_img, db_path=images_folder_path, detector_backend='retinaface', model_name='ArcFace', enforce_detection=False)
print(df)

fig = plt.figure()
rows = 1
cols = 2
ax1 = fig.add_subplot(rows, cols, 1)
ax1.imshow(cv2.cvtColor(input_img, cv2.COLOR_BGR2RGB))
ax1.axis("off")

res_img_path = df[0]['identity'].iloc[0]
print("Result Image Path:", res_img_path)
res_img = cv2.imread(str(res_img_path))
ax2 = fig.add_subplot(rows, cols, 2)
ax2.imshow(cv2.cvtColor(res_img, cv2.COLOR_BGR2RGB))
ax2.axis("off")
plt.show()