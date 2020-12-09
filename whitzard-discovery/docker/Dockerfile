FROM docker2.yidian.com:5000/centos/compile-jdk8:20200331

ENV module_name whitzard-discovery
MAINTAINER LiuJie <liujie3@yidian-inc.com>
CMD mkdir -p /home/whitzard/${module_name}
ADD docker/run.sh  /home/whitzard/${module_name}/
ADD target/${module_name}-1.0-SNAPSHOT.jar /home/whitzard/${module_name}/