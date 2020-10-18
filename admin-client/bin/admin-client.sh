#!/bin/bash
#这里可替换为你自己的执行程序，其他代码无需更改
APP_NAME=admin-client.jar
PORT=8080
 
# 使用外部lib:
LIB=lib/
# -Dloader.path=lib/
#使用说明，用来提示输入参数
usage() {
 echo "Usage: sh 脚本名.sh [start|stop|restart|status]"
 exit 1
}
 
#检查程序是否在运行
is_exist(){
 pid=`ps -ef|grep $APP_NAME|grep -v grep|awk '{print $2}' `
 #如果不存在返回1，存在返回0 
 if [ -z "${pid}" ]; then
 return 1
 else
 return 0
 fi
}
 
#启动方法
start(){
 is_exist
 if [ $? -eq "0" ]; then
 echo "${APP_NAME} is already running. pid=${pid} ."
 else
# nohup java -Dloader.path=$LIB -jar /data/project/admin-client/admin-client.jar > /data/project/admin-client/admin-client.log 2>&1 --server.port=$PORT &
 nohup java -jar /data/project/admin-client/admin-client.jar > /data/project/admin-client/admin-client.log 2>&1 --server.port=$PORT &
 echo "${APP_NAME} start success"
 fi
# tail -f /data/project/admin-client/admin-client.log
}
 
#停止方法
stop(){
 is_exist
 if [ $? -eq "0" ]; then
 kill -9 $pid
 else
 echo "${APP_NAME} is not running"
 fi 
}
 
#输出运行状态
status(){
 pid=`ps -ef|grep $APP_NAME|grep -v grep|awk '{print $2}' `
 is_exist
 if [ $? -eq "0" ]; then
 echo "${APP_NAME} is running. Pid is ${pid} and Port is $PORT"
 else
 echo "${APP_NAME} is NOT running."
 fi
}
 
#重启
restart(){
 stop
 start
}
 
#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
 "start")
 start
 ;;
 "stop")
 stop
 ;;
 "status")
 status
 ;;
 "restart")
 restart
 ;;
 *)
 usage
 ;;
esac