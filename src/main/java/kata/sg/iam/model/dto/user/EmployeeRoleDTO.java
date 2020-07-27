package kata.sg.iam.model.dto.user;

import kata.sg.iam.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRoleDTO {

    private String id;
    private String code;
    private String description;

    public EmployeeRoleDTO(Role role) {
        id = role.getId();
        code = (role.getCode());
        description = role.getDescription();
    }

    public Role toRole() {
        return Role.builder()
                .id(getId())
                .code(getCode())
                .description(getDescription())
                .build();
    }
}
