#!/bin/sh
sbtansicolor=${SBTANSICOLOUR:-false}

javaArgs="-Dsbt.boot.directory=project/boot/ -Dsbt.log.noformat=$sbtansicolor -XX:+UseConcMarkSweepGC -XX:ReservedCodeCacheSize=128m -XX:+UseCodeCacheFlushing -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=512m -Xms3G -Xmx3G -Xss1M"

if [[ $1 == "--debug" ]]
then
    shift
    javaArgs+=" -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
fi

sbtJarPath=$(find tools -name sbt-launch-0.12.2.jar)

java $javaArgs -jar $sbtJarPath "$@"