package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa._experimental.jsr338._02_10_03_01;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Data
@Entity
public class TravelProfile implements Serializable {

    @Id
    private Long id;
}