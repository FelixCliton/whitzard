#!/usr/bin/env bash

module_name=whitzard-discovery
cd /home/whitzard/${module_name}

env=$1

if [[ "$env" = "" ]]
then
 env="prod"
fi

if [[ ! -d logs  ]];then
  mkdir logs
fi
java -jar ${module_name}-1.0-SNAPSHOT.jar --spring.profiles.active=${env} >/dev/null 2>&1