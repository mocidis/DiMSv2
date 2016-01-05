#!/bin/bash
time=`date +%s`
host=$1
service=$2
pipe=$3
echo "[$time] SCHEDULE_FORCED_SVC_CHECK;$host;$service;$time" >> $pipe
