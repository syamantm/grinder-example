#!/bin/bash
rm log/*
java -cp "target/lib/*:target/load-test-1.0-SNAPSHOT.jar" net.grinder.Grinder $1