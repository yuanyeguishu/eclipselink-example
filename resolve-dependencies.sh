#!/bin/bash

set -eu

mvn clean install -DskipTests
mvn dependency:sources
mvn dependency:resolve -Dclassifier=javadoc

