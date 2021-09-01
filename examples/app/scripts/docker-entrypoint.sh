#!/bin/bash

[[ "${JVM_DEBUG}" == "true" ]] && JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,address=8001,server=y,suspend=n"

java ${JAVA_OPTS} -jar template.jar $@
