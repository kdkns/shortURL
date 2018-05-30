#!/bin/bash
for number in {1..10000000}
do

curl -X POST -d 'url=http://www.reddit.com' -H "Content-Type: application/x-www-form-urlencoded" http://localhost:8080/generateCode  &>/dev/null 

if [[ $number%100000 -eq 0 ]] 
then 
 echo $number
fi 

done
exit 0
