#!/bin/bash
time=`date +%s`
host=$1
pipe=$2
echo "[$time] SCHEDULE_FORCED_HOST_CHECK;$host;$time" >> $pipe
