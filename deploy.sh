#!/bin/bash

# 네트워크 생성
echo "Creating network 'app-network'..."
docker network create app-network

# 첫 번째 앱 컨테이너 빌드 및 실행
echo "Building and running 'app' container..."
docker build -t app-image -f Dockerfile .
docker run -d --name app --network app-network -p 8081:8080 --restart unless-stopped app-image

# 두 번째 앱 컨테이너 빌드 및 실행
echo "Building and running 'app2' container..."
docker build -t app2-image -f Dockerfile .
docker run -d --name app2 --network app-network -p 8082:8080 --restart unless-stopped app2-image

# Nginx 컨테이너 실행
echo "Running 'nginx' container..."
docker run -d --name nginx --network app-network -p 80:80 -v $(pwd)/nginx.conf:/etc/nginx/nginx.conf --restart unless-stopped nginx:latest

echo "All services are up and running."
