package kata.sg.iam.model.entity.audit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.Instant;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Audit {

    private Instant createdAt;
    private Instant lastUpdatedAt;

}
