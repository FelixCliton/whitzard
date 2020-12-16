#!/usr/bin/env bash

module_name=whitzard-admin
cd /home/whitzard/${module_name}

env=$1
DEBUG="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5678"
if [[ "$env" = "" ]]
then
 env="prod"
fi

if [[ ! -d logs  ]];then
  mkdir logs
fi
java -jar ${DEBUG} ${module_name}-1.0-SNAPSHOT.jar --spring.profiles.active=${env} >/dev/null 2>&1