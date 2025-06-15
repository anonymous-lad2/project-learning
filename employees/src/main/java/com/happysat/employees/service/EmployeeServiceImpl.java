package com.happysat.employees.service;

import com.happysat.employees.dao.EmployeeDao;
import com.happysat.employees.entity.Employee;
import com.happysat.employees.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Employee findById(long id) {
        return employeeDao.findById(id);
    }

    @Transactional
    @Override
    public Employee save(EmployeeRequest employeeRequest) {
        Employee employee = convertToEmployee(0, employeeRequest);
        return employeeDao.save(employee);
    }

    @Transactional
    @Override
    public Employee update(long id, EmployeeRequest employeeRequest) {
        Employee employee = convertToEmployee(id, employeeRequest);
        return employeeDao.save(employee);
    }

    @Override
    public Employee convertToEmployee(long id, EmployeeRequest employeeRequest) {
        return new Employee(
                id,
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getEmail()
        );
    }

    @Transactional
    @Override
    public void deleteEmployee(long id) {

        employeeDao.deleteById(id);
    }
}
