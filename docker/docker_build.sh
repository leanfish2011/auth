# 1、构建Java程序，得到jar包
# 2、将jar包拷贝到docker目录中
# 3、构建镜像

#!/bin/bash

# 整个项目父路径
project_path_prefix="/home/tim/git/ishou"

# 版本
version="v2.0"

# 生成的jar包后缀
jar_name_suffix=".jar"

# 根据各个项目修改
project_name="auth"
sdk_name="auth-sdk"
service_name="auth-server"

function build_image()
{
    project_path=$project_path_prefix"/"$1
    echo "当前项目路径："$project_path

    jar_name=$3""$jar_name_suffix
    echo "待生成镜像的jar包："$jar_name

    cd $project_path

    # 获取当前分支最后commit
    latest_commit_id=$(git rev-parse --short HEAD)

    # 获取当前分支名称
    branch=$(git symbolic-ref --short -q HEAD)

    # 清理target
    cd $project_path"/"$2
    rm -rf target

    cd $project_path"/"$3
    rm -rf target

    # 构建
    cd ../
    mvn package

    # 将jar包拷贝到docker目录中
    cp $project_path"/"$3"/target/"$jar_name $project_path"/docker"
    cd $project_path"/docker"

    # 构建镜像名称 工程名称:版本号_分支名称_日期_时间_commitid
    time=$(date "+%Y%m%d_%H%M%S")
    tag=$version"_"$branch"_"$time"_"$latest_commit_id
    docker_name=$1":"$tag

    # 执行Dockerfile生成镜像
    sudo docker build -t $docker_name .
}

function test(){
    echo $project_path_prefix
    path=$(pwd)
    echo $path
}

# test

# 入口
build_image $project_name $sdk_name $service_name
