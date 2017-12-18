#!/bin/sh

mvn -P release release:clean release:prepare
mvn -P release release:perform