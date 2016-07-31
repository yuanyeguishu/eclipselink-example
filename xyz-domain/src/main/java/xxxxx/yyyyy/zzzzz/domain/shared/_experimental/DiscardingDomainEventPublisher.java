package xxxxx.yyyyy.zzzzz.domain.shared._experimental;

import xxxxx.yyyyy.zzzzz.domain.shared.DomainEvent;
import xxxxx.yyyyy.zzzzz.domain.shared.DomainEventPublisher;

@lombok.extern.slf4j.Slf4j
public class DiscardingDomainEventPublisher implements DomainEventPublisher {

    @Override
    public <T extends DomainEvent> void publish(T domainEvent) {
        log.info(String.format("discard %s [%s]", domainEvent.getClass().getName(), domainEvent.toString()));
    }
}
