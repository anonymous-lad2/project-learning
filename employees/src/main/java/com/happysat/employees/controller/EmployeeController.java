package com.happysat.employees.controller;

import com.happysat.employees.dao.EmployeeDao;
import com.happysat.employees.entity.Employee;
import com.happysat.employees.request.EmployeeRequest;
import com.happysat.employees.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Rest Api Endpoints", description = "Operations related to employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "get all employees", description = "retrieve list of all employees")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @Operation(summary = "Fetch single employee", description = "Retrieve single employee")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable @Min(value = 1) long employeeId){
        return employeeService.findById(employeeId);
    }

    @Operation(summary = "Create new Employee", description = "Add a new employee to db")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        return employeeService.save(employeeRequest);
    }

    @Operation(summary = "update an employee", description = "update the details of a current employee")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable @Min(1) long employeeId, @Valid @RequestBody EmployeeRequest employeeRequest){
        return employeeService.update(employeeId, employeeRequest);
    }

    @Operation(summary = "delete an employee", description = "remove an employee from database")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable @Min(1) long employeeId){
        employeeService.deleteEmployee(employeeId);
    }
}
