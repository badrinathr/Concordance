# Concordance Problem
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
```
a  {2:1,1}
all  {1:2}
alphabetical  {1:2}
an  {2:1,2}
appeared  {1:3}
arbitrary  {1:1}
bonus  {1:3}
concordance  {1:2}
document  {1:1}
e  {1:2}
each  {2:3,3}
english  {1:1}
frequencies  {1:2}
generate  {1:1}
given  {1:1}
i  {1:2}
in  {2:1,3}
label  {1:3}
labeled  {1:2}
list  {1:2}
numbers  {1:3}
occurrence  {1:3}
occurrences  {1:2}
of  {1:2}
program  {1:1}
sentence  {1:3}
text  {1:1}
that  {1:1}
the  {1:3}
which  {1:3}
will  {1:1}
with  {2:2,3}
word  {3:2,2,3}
write  {1:1}
written  {1:1}
```

## Run for any file of your choice

You can provide a single argument to program which should be a fully qualified file name

```
$ mvn -q clean compile exec:java -Dexec.args=<fully qualified file name>
```









