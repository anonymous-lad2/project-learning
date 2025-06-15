package com.happysat.employees.dao;

import com.happysat.employees.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        TypedQuery<Employee> theQuery = entityManager.createQuery("From Employee", Employee.class);

        return theQuery.getResultList();
    }

    @Override
    public Employee findById(long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee save(Employee theEmployee) {
        return entityManager.merge(theEmployee);
    }

    @Override
    public void deleteById(long id) {
        entityManager.remove(entityManager.find(Employee.class, id));
    }
}
