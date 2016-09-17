package xxxxx.yyyyy.zzzzz.xyz.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import xxxxx.yyyyy.zzzzz.xyz.domain.shared.AggregateRoot;

@lombok.EqualsAndHashCode
@lombok.ToString
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
