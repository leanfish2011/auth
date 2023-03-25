#!/bin/bash

# 根据各个项目修改
project_name="auth"
sdk_name="auth-sdk"
service_name="auth-server"

# 引入脚本
source ../../shell/docker_build_service.sh

# 调用引入脚本中方法
build_image $project_name $sdk_name $service_name
