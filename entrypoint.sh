#!/bin/sh
ls -l /ai/conf
# Start the Java application
exec java -Dloader.path=/ai/conf -Djava.security.egd=file:/dev/./urandom -jar /ai/easyQServer.jar