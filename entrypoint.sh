#!/bin/bash
project_name=$1
echo "Trying to find ${project_name} jar file"
find . -type f -depth -maxdepth 3 -iname "${project_name}*.jar" -exec java -jar {} \;
exit
