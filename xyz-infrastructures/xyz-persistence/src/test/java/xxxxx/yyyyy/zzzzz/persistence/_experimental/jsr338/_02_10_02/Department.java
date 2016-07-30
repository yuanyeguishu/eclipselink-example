package xxxxx.yyyyy.zzzzz.persistence._experimental.jsr338._02_10_02;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Data
@Entity
public class Department implements Serializable {

    @Id
    private Long id;
    @OneToMany(mappedBy = "department")
    private Collection<Employee> employees;
}
