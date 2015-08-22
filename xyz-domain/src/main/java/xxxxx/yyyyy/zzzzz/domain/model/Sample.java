package xxxxx.yyyyy.zzzzz.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;

@lombok.Data
@Entity
public class Sample implements AggregateRoot<Sample, Long> {

    private static final long serialVersionUID = 1L;
    @Id //@GeneratedValue
    private Long id;
    @Version
    private Long version;
    private String name;
}
