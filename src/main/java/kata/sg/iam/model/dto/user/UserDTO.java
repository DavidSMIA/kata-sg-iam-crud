package kata.sg.iam.model.dto.user;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {

    private String id;
    private String lastname;
    private String firstname;
    private String email;
    private Set<UserRoleDTO> roles;

}
