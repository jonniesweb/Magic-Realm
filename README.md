# Magic Realm
[![Build Status](https://magnum.travis-ci.com/jonniesweb/Magic-Realm.svg?token=xFTp8CbyAjwMux8WpAcZ&branch=master)](https://magnum.travis-ci.com/jonniesweb/Magic-Realm)

### Zach Chai and Jon Simpson

Environment
Java JDK 1.7
Eclipse Luna Enterprise Edition
Maven 3

Tested on both Linux and Windows

### Viewing source code
Go to src directory and open files in an editor/viewer of your choice

Note: source in com.igormaznitsa.* is a dependency from https://code.google.com/p/jhexed/

### Staring the game

Execute the jar file with the following command (requires JRE 7)

	 java -jar magicrealm-0.0.1-SNAPSHOT-jar-with-dependencies.jar


### Building

- Install JDK 1.7
- Install Maven 3
- in the root directory (directory of pom.xml) run the following command to generate the jar

	mvn clean compile assembly:single

This will download the dependencies and compile the code
