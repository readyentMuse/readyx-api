# AWS 접속
~~~
ssh -i /Users/jeongmin/.ssh/amazon-backend.pem ubuntu@ec2-43-202-109-142.ap-northeast-2.compute.amazonaws.com
~~~

# 파일 업로드
~~~
scp -i ~/.ssh/amazon-backend.pem -r . ubuntu@ec2-43-202-109-142.ap-northeast-2.compute.amazonaws.com
~~~

# AWS 파일 복사
~~~
sudo cp -r /home/ubuntu/* /app
~~~

# Docker Run
~~~
sudo docker-compose up -d --build
~~~


