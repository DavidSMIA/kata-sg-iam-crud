package kata.sg.iam.repository;

import kata.sg.iam.model.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, String> {

}
