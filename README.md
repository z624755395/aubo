# AAS for aubo robot
This project implements some necessary Industry 4.0 components to integrate a aubo robot into an I4.0 system.

## Table of Contents

- [Background](#background)
- [Install](#install)
- [Usage](#usage)
- [Simulate](#simulate)
- [TCPconnection](#TCPconnection)


## Background
In this project, Eclipse Basyx SDK is used.
The documentation of Eclipse Basyx: https://wiki.eclipse.org/BaSyx

## Install
1. Import projects : aubo, basyx.sdk, basyx.components
1. maven clean install for each project

## Usage
1. AASstart.java run as java application
2. Or run java -jar /aubo-0.0.1-SNAPSHOT-jar-with-dependencies.jar
## Simulate
It is suggested to use Node-RED to make an HTTP request.
Node-RED documentation: https://nodered.org/
aubo.json file is for Node-RED 
### TCPconnection
Modify the TCP server address in TCPClient.java in order to connect to a remote TCP server

