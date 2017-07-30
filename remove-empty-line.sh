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
    #test -f $$ && rm $$
    echo "exit"
}

set -e
trap "trap_exit" EXIT

which mvn
if [ $? -eq 0 ]; then
    mvn -T 4.0C clean
fi

find -type f -name '*.java'       | xargs sed -i '/^\s*$/d'
find -type f -name '*.xml'        | xargs sed -i '/^\s*$/d'
#find -type f -name '*.properties' | xargs sed -i '/^\s*$/d'
#find -type f -name '*.jsp'        | xargs sed -i '/^\s*$/d'

#for x in $(find -type f \( -name \*.java -o -name \*.xml \))
#do
#    echo ${x}
#    grep -v '^\s*$' ${x} > /dev/null
#    if [ $? -ne 0 ]; then
#        grep -v '^\s*$' ${x} > ${x}.$$
#        mv ${x}.$$ ${x}
#    fi
#done
