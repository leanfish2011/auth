#!/bin/bash

# 项目信息
version="v2.0"
db_name="auth"

function build_image()
{
    echo "进入mariadb init制作镜像目录："$(pwd)

    latest_commit_id=$(git rev-parse --short HEAD)
    branch=$(git symbolic-ref --short -q HEAD)
    time=$(date "+%Y%m%d_%H%M%S")
    tag=$version"_"$branch"_"$time"_"$latest_commit_id
    docker_name=$db_name"_mariadb_init:"$tag

    sudo docker build -t $docker_name .
}

# 入口
build_image
