Dictionary [![Java CI with Gradle](https://github.com/gaborbata/dictionary/workflows/Java%20CI%20with%20Gradle/badge.svg)](https://github.com/gaborbata/dictionary/actions/workflows/gradle.yml)
==========

Overview
--------
Dictionary is a simple English-Hungarian dictionary application with regular expression support
based on a [deterministic finite-state automata (DFA) implementation](http://www.brics.dk/automaton/).

![Dictionary](https://raw.githubusercontent.com/gaborbata/dictionary/master/resources/dictionary-capture.png)

How to compile
--------------
* Maven: `mvn clean package`
* Gradle: `gradle clean build`

Usage
-----
Java 8 or later is recommended to run the dictionary application.
Most platforms have a mechanism to execute `.jar` files (e.g. double click the `.jar`).
You can also run the application from the command line by typing:

    java -jar dictionary-0.4.0.jar

License
-------
[License for Dictionary](https://raw.githubusercontent.com/gaborbata/dictionary/master/src/main/resources/resources/license.txt)
