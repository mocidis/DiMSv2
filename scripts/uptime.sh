#!/bin/bash
ok=core-well
problem=core-death
ps -ef | awk '{print $8}' | grep icinga2
result=$(echo $?)
if test $result -ne 0
then
echo $problem | nc -u localhost 9999
else echo $ok | nc -u localhost 9999
fi
