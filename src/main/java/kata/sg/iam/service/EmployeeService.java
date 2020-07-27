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
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public Employee createEmployee(EmployeePayload employeePayload) {
        Employee employee = Employee.builder()
                .firstname(employeePayload.getFirstname())
                .lastname(employeePayload.getLastname())
                .build();

        if(employeePayload.getRoles() != null) {
            employee.setRoles(findEmployeeRolesFromCode(employee, employeePayload.getRoles()));
        }

        return employeeRepository.save(employee);
    }

    public Optional<Employee> findEmployeeById(String id) {
        return employeeRepository.findById(UUID.fromString(id));
    }

    public Employee updateEmployee(String userId, EmployeePayload employeePayload) {
        Employee employee = employeeRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new EmployeeNotFoundException(userId));
        if(employeePayload.getRoles() != null) {
            employee.setRoles(findEmployeeRolesFromCode(employee, employeePayload.getRoles()));
        }
        employee.setFirstname(employeePayload.getFirstname());
        employee.setLastname(employeePayload.getLastname());

        return employeeRepository.save(employee);

    }

    private Set<EmployeeRole> findEmployeeRolesFromCode(Employee employee, Set<String> roleCodes) {
        List<Role> allRoles = roleRepository.findAllByCodeIn(roleCodes);
        //TODO : Gerer le cas ou un role n'existe pas.

        return allRoles.stream().map( r-> new EmployeeRole(employee, r)).collect(Collectors.toSet());
    }
}
