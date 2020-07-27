package kata.sg.iam.model.dto.user;

import kata.sg.iam.model.entity.Employee;
import kata.sg.iam.model.entity.EmployeeRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {

    private String id;
    private String lastname;
    private String firstname;
    @Builder.Default
    private Set<EmployeeRoleDTO> roles = new HashSet<>();

    public EmployeeDTO(Employee employee) {
        id = employee.getId();
        firstname = employee.getFirstname();
        lastname = employee.getLastname();
        roles = employee.getRoles().stream().map( r -> new EmployeeRoleDTO(r.getRole())).collect(Collectors.toSet());
    }

    public Employee toEmployee() {
        Employee employee = Employee.builder()
                .id(getId())
                .firstname(getFirstname())
                .lastname(getLastname())
                .build();
        employee.setRoles(roles.stream().map(r -> new EmployeeRole(employee, r.toRole())).collect(Collectors.toSet()));
        return employee;
    }



}
