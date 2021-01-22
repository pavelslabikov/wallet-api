#!/bin/bash
set -e
echo "Trying to find wallet-api.jar file"
if [ -e "./target/wallet-api.jar" ]; then
  echo "Executing java -jar wallet-api.jar"
  java -jar ./target/wallet-api.jar
else
  echo "Unable to find executable jar file in ./target dir" && exit 1
fi
exit
