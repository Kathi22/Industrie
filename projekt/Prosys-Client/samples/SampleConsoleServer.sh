#!/bin/sh

libdir=../lib
bindir=.
srcdir=.

CP=$libdir/Prosys-OPC-UA-Java-SDK-Client-Server-Evaluation-2.1.0-436.jar:$libdir/Opc.Ua.Stack-1.02.336.8.jar:$libdir/log4j-1.2.17.jar:$libdir/slf4j-api-1.7.7.jar:$libdir/slf4j-log4j12-1.7.7.jar:$libdir/bcprov-jdk15on-147.jar:$libdir/bcpkix-jdk15on-147.jar:$libdir/httpclient-4.2.5.jar:$libdir/httpcore-4.2.4.jar:$libdir/httpcore-nio-4.2.4.jar

test -e $bindir/com/prosysopc/ua/samples/server/SampleConsoleServer.class || javac -classpath $CP $srcdir/com/prosysopc/ua/samples/server/*.java

java -classpath $bindir:$CP com.prosysopc.ua.samples.server.SampleConsoleServer $*
