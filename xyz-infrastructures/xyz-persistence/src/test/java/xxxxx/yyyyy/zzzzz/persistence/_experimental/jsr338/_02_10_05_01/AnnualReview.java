package xxxxx.yyyyy.zzzzz.persistence._experimental.jsr338._02_10_05_01;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Data
@Entity
public class AnnualReview implements Serializable {

    @Id
    private Long id;
}
