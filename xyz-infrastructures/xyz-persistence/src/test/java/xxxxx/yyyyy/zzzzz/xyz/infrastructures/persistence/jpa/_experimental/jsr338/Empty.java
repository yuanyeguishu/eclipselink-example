package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa._experimental.jsr338;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Empty implements Serializable {

    @Id
    private Long id;
}
