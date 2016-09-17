#!/bin/bash

set -eu

mvn clean install -DskipTests
mvn clean test
mvn clean verify -Parquillian-glassfish-embedded
mvn archetype:create-from-project

