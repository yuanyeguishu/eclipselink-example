package xxxxx.yyyyy.zzzzz.domain.shared._experimental._testdouble.fake;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;

@lombok.EqualsAndHashCode
@lombok.ToString
@Entity
public class FakeEntity implements AggregateRoot<FakeEntity, Long> {

    private static final long serialVersionUID = 1L;
    @Id //@GeneratedValue
    private Long id;
    @Version
    private Long version;
    private String name;

    public FakeEntity(Long id, String name) {
        setId(id);
        setName(name);
    }

    protected FakeEntity() {
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
