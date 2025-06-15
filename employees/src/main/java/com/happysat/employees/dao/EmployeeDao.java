package com.happysat.employees.dao;

import com.happysat.employees.entity.Employee;
import java.util.List;

public interface EmployeeDao {

    List<Employee> findAll();

    Employee findById(long id);

    Employee save(Employee theEmployee);

    void deleteById(long id);
}
