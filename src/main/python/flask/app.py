from flask import Flask, request, render_template
import boto3
import json
from werkzeug.utils import secure_filename
from socket import *

app = Flask(__name__)

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

if __name__ == "__main__":
    app.run('0.0.0.0', port=5000, debug=True)