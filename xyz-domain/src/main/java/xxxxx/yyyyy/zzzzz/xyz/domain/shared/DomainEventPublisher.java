package xxxxx.yyyyy.zzzzz.xyz.domain.shared;

public interface DomainEventPublisher {

    <T extends DomainEvent> void publish(T domainEvent);
}
