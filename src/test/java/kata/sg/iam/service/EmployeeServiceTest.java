package kata.sg.iam.service;

import kata.sg.iam.database.AbstractDatabaseTest;
import kata.sg.iam.model.dto.user.EmployeePayload;
import kata.sg.iam.model.entity.Employee;
import kata.sg.iam.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class EmployeeServiceTest extends AbstractDatabaseTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void should_find_all_employee() {

        employeeRepository.deleteAll();
        employeeRepository.saveAll(createEmployees(2));

        List<Employee> employees = employeeService.getEmployees(0, 10);
        assertThat(employees.size()).isEqualTo(2);
    }

    @Test
    public void should_find_ten_first_employee() {

        employeeRepository.saveAll(createEmployees(20));

        List<Employee> employees = employeeService.getEmployees(0, 10);
        assertThat(employees.size()).isEqualTo(10);
    }

    @Test
    public void should_create_employee_without_role() {

        EmployeePayload expectedEmployee = EmployeePayload.builder()
                .firstname("new")
                .lastname("employee")
                .build();
        Employee savedEmployee = employeeService.createEmployee(expectedEmployee);
        assertThat(savedEmployee.getFirstname()).isEqualTo(expectedEmployee.getFirstname());
        assertThat(savedEmployee.getLastname()).isEqualTo(expectedEmployee.getLastname());
    }

    @Test
    public void should_create_employee_with_role() {

        EmployeePayload expectedEmployee = EmployeePayload.builder()
                .firstname("new")
                .lastname("employee")
                .roles(new HashSet<String>(){{
                    add("DEV");
                }})
                .build();

        Employee savedEmployee = employeeService.createEmployee(expectedEmployee);

        assertThat(savedEmployee.getFirstname()).isEqualTo(expectedEmployee.getFirstname());
        assertThat(savedEmployee.getLastname()).isEqualTo(expectedEmployee.getLastname());
    }


    @Test
    public void should_update_employee_without_role() {

        Employee existingEmployee = employeeRepository.save(createEmployees(1).get(0));
        EmployeePayload expectedEmployee = EmployeePayload.builder()
                .firstname("modif")
                .lastname("employee")
                .build();

        Employee savedEmployee = employeeService.updateEmployee(existingEmployee.getId().toString(), expectedEmployee);
        assertThat(savedEmployee.getFirstname()).isEqualTo(expectedEmployee.getFirstname());
        assertThat(savedEmployee.getLastname()).isEqualTo(expectedEmployee.getLastname());
    }


    private List<Employee> createEmployees(int nbEmployees) {
        return IntStream.range(0, nbEmployees)
                .mapToObj(i -> Employee.builder()
                        .firstname("Mick")
                        .lastname(" Employee" + i)
                        .build())
                .collect(Collectors.toList());
    }

}