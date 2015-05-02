# Magic Realm
[![Build Status](https://travis-ci.org/jonniesweb/Magic-Realm.svg)](https://travis-ci.org/jonniesweb/Magic-Realm)

### Zach Chai and Jon Simpson

Environment
Java JDK 1.7
Eclipse Luna Enterprise Edition
Maven 3

Tested on both Linux and Windows

### Viewing source code
Go to src directory and open files in an editor/viewer of your choice

Note: source in com.igormaznitsa.* is a dependency from https://code.google.com/p/jhexed/

### Starting the server

Execute the jar file with the following command (requires JRE 7+)

	 java -jar magicrealm-server.jar
	 
### Starting the client
Execute the jar file with the following command (requires JRE 7+). Each player should launch a client.

	java -jar magicrealm-client.jar


### Building

- Install JDK 1.7
- Install Maven 3
- in the root directory (directory of pom.xml) run the following command to generate the jar

	mvn clean package

This will download the dependencies and compile the code. It will output the client and server jars in the target/ directory.
