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

import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import xxxxx.yyyyy.zzzzz.xyz.application._experimental.cdi.event.Bar;
import xxxxx.yyyyy.zzzzz.xyz.application._experimental.cdi.event.BarEvent;
import xxxxx.yyyyy.zzzzz.xyz.application._experimental.cdi.event.EventProducer;
import xxxxx.yyyyy.zzzzz.xyz.application._experimental.cdi.event.Foo;
import xxxxx.yyyyy.zzzzz.xyz.application._experimental.cdi.event.FooEvent;

public class EventProducerImpl implements EventProducer {

    @Inject @Any Event<FooEvent> anyFooEvent;
    @Inject @Foo Event<FooEvent> fooFooEvent;
    @Inject @Any Event<BarEvent> anyBarEvent;
    @Inject @Bar Event<BarEvent> barBarEvent;

    @Override
    public void notifyObservers() {
        anyFooEvent.fire(new FooEvent("anyFooEvent.fire"));
        fooFooEvent.fire(new FooEvent("fooFooEvent.fire"));
        anyBarEvent.fire(new BarEvent("anyBarEvent.fire"));
        barBarEvent.fire(new BarEvent("barBarEvent.fire"));
    }
}
