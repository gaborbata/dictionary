Metis Dictionary [![Build Status](https://travis-ci.org/gaborbata/metis-dictionary.svg?branch=master)](https://travis-ci.org/gaborbata/metis-dictionary)
================

Overview
--------
Metis Dictionary is a simple English-Hungarian dictionary application with regular expression support
based on a [deterministic finite-state automata (DFA) implementation](http://www.brics.dk/automaton/).

![Metis Dictionary](https://raw.githubusercontent.com/gaborbata/metis-dictionary/master/resources/dictionary-capture.gif)

*About the name: In Greek Mythology, Metis is the goddess of practical and intellectual wisdom.*

How to compile
--------------
* Maven: `mvn clean package`
* Gradle: `gradle clean build`

Usage
-----
Java 6 or later is recommended to run the dictionary application. Most platforms have a mechanism to execute `.jar` files (e.g. double click the `dictionary-0.3.8.jar`).
You can also run the application from the command line by typing:

    java -jar dictionary-0.3.8.jar

License
-------
Copyright (c) 2008-2012 Gabor Bata

All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
3. The name of the author may not be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE AUTHOR "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

---

This software includes dk.brics.automaton version 1.11-1, covered by the following license:

Copyright (c) 2001-2008 Anders Moeller

All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
3. The name of the author may not be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE AUTHOR "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

---

This software uses icons from the [Tango base icon theme](http://tango.freedesktop.org/Tango_Desktop_Project).

The Tango base icon theme is licensed under the [Creative Commons Attribution Share-Alike license](http://creativecommons.org/licenses/by-sa/2.5/).

---

This software uses icons from the [Silk icon set by Mark James](http://www.famfamfam.com/lab/icons/silk/).

The Silk icon set is licensed under a [Creative Commons Attribution 2.5 License](http://creativecommons.org/licenses/by/2.5/).
