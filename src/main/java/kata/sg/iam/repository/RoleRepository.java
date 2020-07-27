package kata.sg.iam.repository;

import kata.sg.iam.model.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, UUID> {
    public List<Role> findAllByCodeIn(Iterable<String> codes);
}
