#!/bin/bash

# Compiles TSPLIB4J
javac org/moeaframework/problem/tsplib/*.java

# Compiles out lib
javac -cp .:org.moeaframework.problem.tsplib.* lib/*.java

# Compiles the tester class
javac -cp .:org.moeaframework.problem.tsplib.* main/MetaheuristicsRunner.java
