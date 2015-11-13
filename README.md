#. Concordance Problem
Given an arbitrary text document written in English, write a program that will generate a concordance, i.e. an alphabetical list of all word occurrences, labelled with word frequencies.

Bonus: label each word with the sentence numbers in which each occurrence appeared.

## Install

You'll need
 - JDK 8 - tested with version 1.8.0.66
 - Maven
 - Git bash client

```
$ git clone -s  https://github.com/badrinathr/Concordance.git
$ cd Concordance

```

## Run with the sample file packaged

```
$ mvn -q clean compile exec:java
```

The output will look as follows



## Run for any file of your choice

You can provide a single argument to program which should be a fully qualified file name

```
$ mvn -q clean compile exec:java -Dexec.args=<fully qualified file name>
```









