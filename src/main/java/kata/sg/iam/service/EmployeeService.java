package kata.sg.iam.service;

import kata.sg.iam.model.entity.Employee;
import kata.sg.iam.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/** TODO : Voir si on conserve une couche service ou directement Controller -> repository **/
@Component
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees(int page, int size) {
        return employeeRepository.findAll(PageRequest.of(page, size)).toList();
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findEmployeeById(String id) {
        return employeeRepository.findById(id);
    }
}
