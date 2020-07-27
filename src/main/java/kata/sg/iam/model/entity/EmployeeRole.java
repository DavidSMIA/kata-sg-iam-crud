package kata.sg.iam.model.entity;


import kata.sg.iam.model.entity.audit.Audit;
import kata.sg.iam.model.entity.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "employee_role")
public class EmployeeRole implements Auditable {

    @EmbeddedId
    private EmployeeRoleId id;

    @ManyToOne
    @MapsId("employeeId")
    private Employee employee;

    @ManyToOne
    @MapsId("roleId")
    private Role role;

    @Embedded
    private Audit audit;

    public EmployeeRole(Employee employee, Role role) {
        this.employee = employee;
        this.role = role;
        this.id = new EmployeeRoleId(employee.getId(), role.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        EmployeeRole that = (EmployeeRole) o;
        return Objects.equals(employee, that.employee) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, role);
    }
}
