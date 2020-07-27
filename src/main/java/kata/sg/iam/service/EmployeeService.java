package kata.sg.iam.service;

import kata.sg.iam.model.dto.user.EmployeePayload;
import kata.sg.iam.model.entity.Employee;
import kata.sg.iam.model.entity.EmployeeRole;
import kata.sg.iam.model.entity.Role;
import kata.sg.iam.model.exception.EmployeeNotFoundException;
import kata.sg.iam.repository.EmployeeRepository;
import kata.sg.iam.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/** TODO : Voir si on conserve une couche service ou directement Controller -> repository **/
@Component
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private RoleRepository roleRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
    }

    public List<Employee> getEmployees(int page, int size) {
        return employeeRepository.findAll(PageRequest.of(page, size)).toList();
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findEmployeeById(String id) {
        return employeeRepository.findById(UUID.fromString(id));
    }

    public Employee updateEmployee(String userId, EmployeePayload userPayload) {
        Employee employee = employeeRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new EmployeeNotFoundException(userId));
        List<Role> allRoles = roleRepository.findAllByCodeIn(userPayload.getRoles());

        employee.setRoles(allRoles.stream().map( r-> new EmployeeRole(employee, r)).collect(Collectors.toSet()));
        employee.setFirstname(userPayload.getFirstname());
        employee.setLastname(userPayload.getLastname());

        return employeeRepository.save(employee);

    }
}
