#!/bin/bash
host=$1
service=$2
time=$3
pipe=$4
echo "[$time] SCHEDULE_SVC_CHECK;$host;$service;$time" >> $pipe
