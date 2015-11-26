#!/bin/sh
java -cp target/zoo-loader-0.1-SNAPSHOT-jar-with-dependencies.jar:`/usr/libexec/java_home`/lib/tools.jar \
    com.turbolent.zoo.Loader `pwd`/target/zoo-agent-0.1-SNAPSHOT-jar-with-dependencies.jar $1 $2
