package xxxxx.yyyyy.zzzzz.domain.model;

import java.util.Date;
import xxxxx.yyyyy.zzzzz.domain.shared.DomainEvent;

@lombok.EqualsAndHashCode
@lombok.ToString
public class SampleCreated implements DomainEvent {

    private final Date occurredOn;

    public SampleCreated() {
        this.occurredOn = new Date();
    }

    @Override
    public Date occurredOn() {
        return this.occurredOn;
    }
}
