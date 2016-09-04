package xxxxx.yyyyy.zzzzz.persistence._experimental.jsr338._02_10_01;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Data
@Entity
public class Employee implements Serializable {

    @Id
    private Long id;
    @OneToOne
    private Cubicle assignedCubicle;
}