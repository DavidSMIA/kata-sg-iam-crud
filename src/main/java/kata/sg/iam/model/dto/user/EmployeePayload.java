package kata.sg.iam.model.dto.user;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class EmployeePayload {
    private String lastname;
    private String firstname;
    private Set<String> roles;
}
