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

function trap_exit() {
    if [ -f target/pid ]; then
        declare -r pid=$(cat target/pid)
        echo "pid=${pid}"
        kill ${pid}
    fi
    echo "exit"
}

set -eu

trap "trap_exit" EXIT

#declare -r PROCESSORS=$(cat /proc/cpuinfo | grep -E '^processor.*' | wc -l)

test -d ./target && rm -r ./target

java -version
mvn --version



declare -r PAYARA_MICRO_GROUP_ID=fish.payara.extras
declare -r PAYARA_MICRO_ARTIFACT_ID=payara-micro
declare -r PAYARA_MICRO_VERSION=5.0.0.Alpha3

declare -r PAYARA_MICRO="${HOME}/.m2/repository/\
$(echo ${PAYARA_MICRO_GROUP_ID} | sed -e 's|\.|/|g')/\
${PAYARA_MICRO_ARTIFACT_ID}/\
${PAYARA_MICRO_VERSION}/\
${PAYARA_MICRO_ARTIFACT_ID}-${PAYARA_MICRO_VERSION}.jar"

if [ ! -f ${PAYARA_MICRO} ]; then
    mvn org.apache.maven.plugins:maven-dependency-plugin:3.0.2:get \
        -DgroupId=${PAYARA_MICRO_GROUP_ID} \
        -DartifactId=${PAYARA_MICRO_ARTIFACT_ID} \
        -Dversion=${PAYARA_MICRO_VERSION}
fi



mvn -T 4.0C clean install -DskipTests
mvn -T 4.0C clean test



## -----
mvn -T 4.0C clean package -DskipTests -Pdistribution -pl xyz-interfaces/xyz-api/ -am
mvn -T 4.0C clean package -DskipTests -Pit -pl xyz-env

pushd ./xyz-interfaces/xyz-api/target
mkdir -p                                                        ./WEB-INF/lib/
cp -p       ../../../xyz-env/target/xyz-env-2.0.0-SNAPSHOT.jar  ./WEB-INF/lib/
jar -uvf    xyz-api-2.0.0-SNAPSHOT.war                          ./WEB-INF/lib/
popd

java \
    --add-opens=java.base/jdk.internal.loader=ALL-UNNAMED \
    --add-modules=java.se.ee \
    --illegal-access=permit \
    -jar \
        ${PAYARA_MICRO} \
    --deploy \
        ./xyz-interfaces/xyz-api/target/xyz-api-2.0.0-SNAPSHOT.war \
        &

    #--addJars \
    #    ./xyz-env/target/xyz-env-2.0.0-SNAPSHOT.jar \

test -d target || mkdir target
echo $! > target/pid;

sleep 60

RESPONSE=$(\
    curl --silent --write-out "HTTP_CODE:%{http_code}" \
    -X POST 'http://localhost:8080/xyz-api-2.0.0-SNAPSHOT/samples' \
    -H "Content-Type: application/json" \
    -d '{"id":1,"name":"jdk9"}' \
)
CODE=$(echo ${RESPONSE} | sed 's/^.*HTTP_CODE:\([1-5][0-9][0-9]\)$/\1/g')
BODY=$(echo ${RESPONSE} | sed 's/HTTP_CODE:\([1-5][0-9][0-9]\)$//g')
echo "CODE=[${CODE}]"
echo "BODY=[${BODY}]"
if [ "${CODE}" != "201" ]; then
    exit 1;
fi


RESPONSE=$(\
    curl --silent --write-out "HTTP_CODE:%{http_code}" \
    -X GET 'http://localhost:8080/xyz-api-2.0.0-SNAPSHOT/samples' \
    -H "Accept: application/json" \
)
CODE=$(echo ${RESPONSE} | sed 's/^.*HTTP_CODE:\([1-5][0-9][0-9]\)$/\1/g')
BODY=$(echo ${RESPONSE} | sed 's/HTTP_CODE:\([1-5][0-9][0-9]\)$//g')
echo "CODE=[${CODE}]"
echo "BODY=[${BODY}]"
if [ "${CODE}" != "200" ]; then
    exit 1;
fi
## -----



#mvn -T 4.0C clean verify -Parquillian-glassfish-embedded
#mvn -T 4.0C clean verify -Parquillian-glassfish-managed
#mvn -T 4.0C clean verify -Parquillian-payara-managed
#mvn -T 4.0C clean verify -Parquillian-wildfly-managed
mvn archetype:create-from-project
