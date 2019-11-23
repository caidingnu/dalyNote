#!/bin/bash
#这里可替换为你自己的执行程序，其他代码无需更改
APP_NAME=menuztree-0.0.1-SNAPSHOT.war
#使用说明，用来提示输入参数
usage() {
    echo "Usage: sh 执行脚本.sh [start|stop|restart|status]"
    exit 1
}
#检查程序是否在运行
is_exist(){
  pid=`ps -ef|grep $APP_NAME|grep -v grep|awk '{print $2}'`
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
    echo "${APP_NAME} is running. pid=${pid} ."
  else
   creare_log
    nohup java -jar $APP_NAME >> logs/log.out 2>&1 &
    echo "${APP_NAME} run successful!. pid=${pid} ."
  fi
}

#创建日志文件
creare_log(){
 #首先判断目录是否存在，如果不存在则创建，存在则不再创建
if [ ! -d "./logs" ]
then
#echo "目录不存在"
mkdir logs
fi
#在创建的目录下面创建日志文件
touch ./logs/log.out
}


#停止方法
stop(){
  is_exist
  if [ $? -eq "0" ]; then
    kill -9 $pid
    echo "${APP_NAME} has been closed!"
  else
    echo "${APP_NAME} no running!"
  fi
}

#初始化符号
initfuhao(){
sed -i 's/\r//g' boot.sh
}

#输出运行状态
status(){
  is_exist
  if [ $? -eq "0" ]; then
    echo "${APP_NAME} is running!- Pid is ${pid}"
  else
    echo "${APP_NAME} no running!."
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