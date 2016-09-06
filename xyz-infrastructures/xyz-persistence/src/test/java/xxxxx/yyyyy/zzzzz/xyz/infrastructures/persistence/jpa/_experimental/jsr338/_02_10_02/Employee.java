package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa._experimental.jsr338._02_10_02;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Data
@Entity
public class Employee implements Serializable {

    @Id
    private Long id;
    @ManyToOne
    private Department department;
}
