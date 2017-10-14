/**
 * Copyright © 2015-2017 Masamitsu Namioka (masamitsunamioka@gmail.com)
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
package xxxxx.yyyyy.zzzzz.xyz.domain.model.sample;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import xxxxx.yyyyy.zzzzz.xyz.domain.shared.AggregateRoot;
//@lombok.EqualsAndHashCode
//@lombok.ToString

@Entity
public class Sample implements AggregateRoot<Sample, Long> {

    private static final long serialVersionUID = 1L;
    @Id //@GeneratedValue
    private Long id;
    @Version
    private Long version;
    private String name;

    public Sample(Long id, String name) {
        setId(id);
        setName(name);
        //ServiceLocator.get(DomainEventPublisher.class).forEach(x -> x.publish(new SampleCreated()));
    }

    protected Sample() {
    }

    @Override
    public Long id() {
        return this.id;
    }

    @Override
    public Long version() {
        return this.version;
    }

    public String name() {
        return this.name;
    }

    private void setId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("'id' must not be null");
        }
        this.id = id;
    }

    private void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("'name' must not be empty");
        }
        this.name = name;
    }
}
