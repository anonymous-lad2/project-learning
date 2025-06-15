package com.happysat.employees.service;

import com.happysat.employees.entity.Employee;
import com.happysat.employees.request.EmployeeRequest;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(long id);

    Employee save(EmployeeRequest employeeRequest);

    Employee update(long id, EmployeeRequest employeeRequest);

    Employee convertToEmployee(long id, EmployeeRequest employeeRequest);

    void deleteEmployee(long id);
}
