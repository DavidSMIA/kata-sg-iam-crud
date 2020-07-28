package kata.sg.iam.controller;


import io.swagger.v3.oas.annotations.Operation;
import kata.sg.iam.model.dto.user.EmployeeDTO;
import kata.sg.iam.model.dto.user.EmployeePayload;
import kata.sg.iam.model.exception.EmployeeNotFoundException;
import kata.sg.iam.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Get all employee", tags = { "employee" })
    @GetMapping
    public List<EmployeeDTO> getEmployee() {
        return employeeService.getEmployees(0,10).stream().map(EmployeeDTO::new).collect(Collectors.toList());
    }

    @Operation(summary = "Get employee by id", tags = { "employee" })
    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable("id") String employeeId) {
        return employeeService.findEmployeeById(employeeId).map(EmployeeDTO::new).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    }

    @Operation(summary = "Update employee", tags = { "employee" })
    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable("id") String userId, @RequestBody EmployeePayload userPayload) {
        return new EmployeeDTO(employeeService.updateEmployee(userId, userPayload));
    }

    @Operation(summary = "Create employee", tags = { "employee" })
    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeePayload userPayload) {
        return new EmployeeDTO(employeeService.createEmployee(userPayload));
    }

    @Operation(summary = "Delete employee by id", tags = { "employee" })
    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable("id") String employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}
