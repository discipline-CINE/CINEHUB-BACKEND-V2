import pickle
import os

current_path = os.path.dirname(os.path.realpath(__file__))
images_folder_path = os.path.join(current_path, '../../resources/images/face/ds_arcface_retinaface_v2.pkl')

with open(images_folder_path, 'rb') as f:
    data = pickle.load(f)
    print(data)