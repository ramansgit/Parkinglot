#!/bin/sh
mvn clean
mvn install
cd target
input=$1

if [ ! -z $input ];
then
  java -jar Parkinglot-0.0.1-SNAPSHOT.jar ../$input 
else
   java -jar Parkinglot-0.0.1-SNAPSHOT.jar
fi

