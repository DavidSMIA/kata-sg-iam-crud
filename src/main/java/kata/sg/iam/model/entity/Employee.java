package kata.sg.iam.model.entity;

import kata.sg.iam.model.entity.audit.Audit;
import kata.sg.iam.model.entity.audit.Auditable;
import kata.sg.iam.model.entity.audit.AuditingListener;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingListener.class)
@Entity(name = "employee")
public class Employee implements Auditable {

    @Id
    private String id;
    private String lastname;
    private String firstname;
    @Embedded
    private Audit audit;

    @OneToMany(
            mappedBy = "employee",
            fetch = FetchType.LAZY,
            cascade =  { CascadeType.PERSIST,  CascadeType.MERGE }
    )
    private Set<EmployeeRole> roles;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(lastname, employee.getLastname()) &&  Objects.equals(firstname, employee.getFirstname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastname, firstname);
    }

    public static EmployeeBuilder builder() {
        return new EmployeeBuilder(){
            @Override
            public Employee build() {
                prebuild();
                return super.build();
            }
        };
    }

    public static class EmployeeBuilder  {
        void prebuild(){
           if(this.id == null) {
               id(UUID.randomUUID().toString());
           }
        }
    }

}
