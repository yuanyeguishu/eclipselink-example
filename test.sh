#!/bin/bash

set -eu

#declare -r PROCESSORS=$(cat /proc/cpuinfo | grep -E '^processor.*' | wc -l)

mvn -T 1.0C clean install -DskipTests --quiet
mvn -T 1.0C clean test
mvn -T 1.0C clean verify -Parquillian-glassfish-embedded
mvn -T 1.0C clean verify -Parquillian-payara-managed
mvn -T 1.0C clean verify -Parquillian-wildfly-managed
mvn archetype:create-from-project
