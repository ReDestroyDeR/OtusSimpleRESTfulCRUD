#!/bin/bash
PROJECT_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
mvn clean package
mkdir -p ./target/dependency
cd ./target/dependency || exit
jar -xf ../*.jar
cd ../..
sudo docker build -t redestroyder/simple_restful_crud:"$PROJECT_VERSION" .