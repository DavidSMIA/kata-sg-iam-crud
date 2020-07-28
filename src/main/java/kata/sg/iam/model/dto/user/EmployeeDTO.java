package kata.sg.iam.model.dto.user;

import kata.sg.iam.model.entity.Employee;
import kata.sg.iam.model.entity.EmployeeRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {

    private UUID id;
    private String lastname;
    private String firstname;
    @Builder.Default
    private Set<EmployeeRoleDTO> roles = new HashSet<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EmployeeDTO(Employee employee) {
        id = employee.getId();
        firstname = employee.getFirstname();
        lastname = employee.getLastname();
        if(employee.getRoles() != null) {
            roles = employee.getRoles().stream().map(r -> new EmployeeRoleDTO(r.getRole())).collect(Collectors.toSet());
        }
        createdAt = LocalDateTime.ofInstant(employee.getAudit().getCreatedAt(), ZoneOffset.UTC);
        updatedAt = LocalDateTime.ofInstant(employee.getAudit().getLastUpdatedAt(), ZoneOffset.UTC);
    }

}
