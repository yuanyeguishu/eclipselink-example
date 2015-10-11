package xxxxx.yyyyy.zzzzz.domain.shared;

public interface DomainEventPublisher {

    <T extends DomainEvent> void publish(T domainEvent);
}
