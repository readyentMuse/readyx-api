# AWS 설정
~~~
#도커, 도커 컴포즈 설치
1.1. 필수 패키지 업데이트
sudo apt-get update
sudo apt-get install apt-transport-https ca-certificates curl software-properties-common
1.2. Docker 공식 GPG 키 추가

#폴더 생성
sudo mkdir app
sudo chmod a+w app/
~~~

# 파일 업로드
~~~
 scp -i ~/.ssh/amazon-backend.pem build/libs/readyx-0.0.1-SNAPSHOT.jar Dockerfile docker-compose.yml nginx.conf ubuntu@ec2-43-203-142-146.ap-northeast-2.compute.amazonaws.com:/app  
~~~

# AWS 접속
~~~
신규
ssh -i /Users/jeongmin/.ssh/amazon-backend.pem ubuntu@ec2-43-203-142-146.ap-northeast-2.compute.amazonaws.com
제거
ssh -i /Users/jeongmin/.ssh/amazon-backend.pem ubuntu@ec2-43-202-109-142.ap-northeast-2.compute.amazonaws.com
~~~

# AWS 파일 복사
~~~
sudo cp -r /home/ubuntu/* /app
~~~

# Docker Down
~~~
sudo docker-compose down
~~~

# Docker Run
~~~
sudo docker-compose up -d --build
~~~


