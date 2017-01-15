/**
 * Copyright © 2015 Masamitsu Namioka (masamitsunamioka@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa._experimental;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

@FunctionalInterface
public interface ToCriteriaDeleteFunction<T> {

    CriteriaDelete<T> apply(CriteriaBuilder x, CriteriaDelete<T> y, Root<T> z);
}
