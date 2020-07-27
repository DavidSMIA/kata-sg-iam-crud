package kata.sg.iam.repository;

import kata.sg.iam.model.entity.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, String> {

}
