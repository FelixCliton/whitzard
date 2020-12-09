#!/bin/bash

# 帮助信息
function help_info ()
{
    echo "
    命令示例：sh build.sh -e prod -v 1.0.0 -c '测试' -U
    参数说明:
        -c:comment      备注说明
        -e:environment  系统环境(prod:生成环境, dev:测试环境)（默认为dev）
        -v:version      docker镜像版本号（dev环境默认为latest，prod环境默认为当前日期）
        -h:help         帮助命令
        -U:update       强制更新maven快照包（默认不更新快照包）
    "
}

# 检查命令执行是否成功
function check_cmd_result ()
{
    if [ $? -ne 0 ];then
        echo "执行失败，退出程序~"
        exit 1
    else
        echo "执行成功!"
    fi
}



module_name=whitzard-auth
# 参数解析
while getopts ":c:e:v:hU" opt
do
    case $opt in
        c)
            comment=$OPTARG
            ;;
        e)
            environment=$OPTARG
            ;;
        U)
            update=-U
            ;;
        h)
            help_info
            exit 0
            ;;
        v)
            version=$OPTARG
            exit 0
            ;;
        ?)
            echo "无效的参数"
            help_info
            exit 1
            ;;
    esac
done

if [[ "$environment" = "" ]]
then
 environment=dev
fi

if [[ "$environment" = "prod" ]]
then
    default_ver=`date +%Y%m%d`
else
    default_ver=latest
fi

if [[ "$version" = "" ]]
then
 version=${default_ver}
fi

echo "备注信息        :" ${comment}
echo "Docker 镜像版本 :" ${version}
echo "环境            :" ${environment}
echo "强制更新快照包   :" ${update}

# 编译打包
echo "=============1.编译打包============="
mvn clean package ${update} -Dmaven.test.skip=true
check_cmd_result

docker_image=${module_name}-${environment}:${version}
echo "=============2.生成Docker镜像============="
sudo docker build -f docker/Dockerfile -t=docker2.yidian.com:5000/centos7/${docker_image} .
check_cmd_result

echo "=============3.推送到Docker仓库============="
sudo docker push docker2.yidian.com:5000/centos7/${docker_image}
check_cmd_result

# 版本记录
cur_time=`date`
# 删除本地镜像
echo "=============7.删除本地镜像============="
sudo docker images -a | grep ${module_name} | awk '{print $3}'| sudo xargs docker rmi
check_cmd_result