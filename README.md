Metis Dictionary [![Java CI with Gradle](https://github.com/gaborbata/metis-dictionary/workflows/Java%20CI%20with%20Gradle/badge.svg)](https://github.com/gaborbata/metis-dictionary/actions/workflows/gradle.yml)
================

Overview
--------
Metis Dictionary is a simple English-Hungarian dictionary application with regular expression support
based on a [deterministic finite-state automata (DFA) implementation](http://www.brics.dk/automaton/).

![Metis Dictionary](https://raw.githubusercontent.com/gaborbata/metis-dictionary/master/resources/dictionary-capture.png)

*About the name: In Greek Mythology, Metis is the goddess of practical and intellectual wisdom.*

How to compile
--------------
* Maven: `mvn clean package`
* Gradle: `gradle clean build`

Usage
-----
Java 8 or later is recommended to run the dictionary application. Most platforms have a mechanism to execute `.jar` files (e.g. double click the `dictionary-0.3.9.jar`).
You can also run the application from the command line by typing:

    java -jar dictionary-0.3.9.jar

License
-------
[License for Metis Dictionary](https://raw.githubusercontent.com/gaborbata/metis-dictionary/master/src/main/resources/resources/license.txt)
