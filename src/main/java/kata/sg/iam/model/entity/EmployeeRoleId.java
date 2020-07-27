package kata.sg.iam.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class EmployeeRoleId implements Serializable {

    private String employeeId;
    private String roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        EmployeeRoleId that = (EmployeeRoleId) o;
        return Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, roleId);
    }
}
