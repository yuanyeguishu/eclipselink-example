package xxxxx.yyyyy.zzzzz.persistence._experimental.jsr338._02_10_04;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Data
@Entity
public class Employee implements Serializable {

    @Id
    private Long id;
    @ManyToMany(mappedBy = "employees")
    private Collection<Project> projects;
}
