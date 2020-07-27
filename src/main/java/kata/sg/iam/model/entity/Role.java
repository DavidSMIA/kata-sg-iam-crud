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
@Entity(name = "role")
public class Role implements Auditable {

    @Id
    private String id;
    private String code;
    private String description;
    @Embedded
    private Audit audit;

    @OneToMany(
            mappedBy = "role",
            fetch = FetchType.LAZY,
            cascade =  { CascadeType.PERSIST,  CascadeType.MERGE }
    )
    private Set<EmployeeRole> employees;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(code, role.getCode())
                &&  Objects.equals(code, role.getCode())
                && Objects.equals(id, role.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    public static RoleBuilder builder() {
        return new RoleBuilder(){
            @Override
            public Role build() {
                prebuild();
                return super.build();
            }
        };
    }

    public static class RoleBuilder  {
        void prebuild(){
            if(this.id == null) {
                id(UUID.randomUUID().toString());
            }
        }
    }

}
