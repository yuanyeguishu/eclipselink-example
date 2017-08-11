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
package xxxxx.yyyyy.zzzzz.xyz.application._experimental.cdi.event.impl;

import javax.enterprise.event.Observes;
import xxxxx.yyyyy.zzzzz.xyz.application._experimental.cdi.event.Bar;
import xxxxx.yyyyy.zzzzz.xyz.application._experimental.cdi.event.BarEvent;
import xxxxx.yyyyy.zzzzz.xyz.application._experimental.cdi.event.EventObserver;
import xxxxx.yyyyy.zzzzz.xyz.application._experimental.cdi.event.Foo;
import xxxxx.yyyyy.zzzzz.xyz.application._experimental.cdi.event.FooEvent;

@lombok.extern.slf4j.Slf4j
public class EventObserverImpl implements EventObserver {

    @Override
    public void onAnyFooEvent(final @Observes FooEvent fooEvent) {
        log.info("#onAnyFooEvent {}", fooEvent);
    }

    @Override
    public void onFooFooEvent(final @Observes @Foo FooEvent fooEvent) {
        log.info("#onFooFooEvent {}", fooEvent);
    }

    @Override
    public void onAnyBarEvent(final @Observes BarEvent barEvent) {
        log.info("#onAnyBarEvent {}", barEvent);
    }

    @Override
    public void onBarBarEvent(final @Observes @Bar BarEvent barEvent) {
        log.info("#onBarBarEvent {}", barEvent);
    }
}
