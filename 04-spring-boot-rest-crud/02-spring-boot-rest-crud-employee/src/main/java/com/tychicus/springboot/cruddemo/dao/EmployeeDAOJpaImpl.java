package com.tychicus.springboot.cruddemo.dao;

import com.tychicus.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

//    define fields for entityManager
    private EntityManager entityManager;

//    set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Employee> findAll() {

//        create a  query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee",  Employee.class);

//        execute the query and get a result list
        List<Employee> employees = theQuery.getResultList();

//        return the  result
        return employees;
    }

    @Override
    public Employee findById(int id) {
//        get the employee
        Employee theEmployee = entityManager.find(Employee.class, id);

//        return the employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
//        save the employee
        Employee dbEmployee = entityManager.merge(theEmployee);

//        return the employee
        return dbEmployee;
    }

    @Override
    public void deleteById(int id) {
//        find employee by id
        Employee theEmployee = entityManager.find(Employee.class, id);

//        remove employee
        entityManager.remove(theEmployee);

    }
}


