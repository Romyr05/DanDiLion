#!/bin/bash
set -e


javac -d bin src/*.java
java -cp bin src/testing