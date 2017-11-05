#!/bin/bash
#
# Copyright © 2015-2017 Masamitsu Namioka (masamitsunamioka@gmail.com)
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

set -eu

#declare -r PROCESSORS=$(cat /proc/cpuinfo | grep -E '^processor.*' | wc -l)

# TODO Remove when JDK9 had been available.
export JAVA_HOME=/usr/java/jdk1.8.0_151
export PATH=$JAVA_HOME/bin:$PATH

java -version
mvn --version

mvn -T 4.0C clean install -DskipTests
mvn -T 4.0C clean test
mvn -T 4.0C clean verify -Parquillian-glassfish-embedded
mvn -T 4.0C clean verify -Parquillian-glassfish-managed
#mvn -T 4.0C clean verify -Parquillian-payara-managed
#mvn -T 4.0C clean verify -Parquillian-wildfly-managed
mvn archetype:create-from-project
