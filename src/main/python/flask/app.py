from flask import Flask, request

import json
import os 

app = Flask(__name__)

@app.route('/')
def home():
    return "home"

# Springboot 메인 서버에서 json 데이터 받아옴
@app.route('/abstractive', methods=['POST'])
def abstractive():
    dto_json = request.get_json()
    
    current_directory = os.path.dirname(os.path.abspath(__file__))
    data_folder_path = os.path.join(current_directory, 'data')
    file_list = os.listdir(data_folder_path)
    
    max_file_num = max(file_list)[:-5]
    next_file_name = str(int(max_file_num) + 1) + ".json"
    path = "data/" + next_file_name
    
    with open(path, 'w+') as f:
        json.dump(dto_json, f, indent=4)
        
    with open("response/response.json", "r", encoding="utf-8") as f:
        json_data = json.load(f)
        response = json.dumps(json_data, ensure_ascii=False)
        
    return response

if __name__ == "__main__":
    app.run('0.0.0.0', port=5000, debug=True)