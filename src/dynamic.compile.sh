#!/bin/bash

javac -cp .:hamcrest-core-1.3.jar:junit.jar:junit-4.12.jar lib/tests/*.java
javac -cp .:hamcrest-core-1.3.jar:junit.jar:junit-4.12.jar lib/Activity.java lib/Box.java lib/Graph.java lib/Item.java lib/DynamicAlgorithms.java
javac -cp .:hamcrest-core-1.3.jar:junit.jar:junit-4.12.jar main/DynamicRunner.java
