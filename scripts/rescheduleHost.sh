#!/bin/bash
host=$1
time=$2
pipe=$3
echo "[$time] SCHEDULE_HOST_CHECK;$host;$time" >> $pipe
