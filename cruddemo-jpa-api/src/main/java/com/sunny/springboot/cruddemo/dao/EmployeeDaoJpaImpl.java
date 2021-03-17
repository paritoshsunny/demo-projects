package com.sunny.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunny.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDaoJpaImpl implements EmployeeDAO {

	private EntityManager entityManager;

	@Autowired
	public EmployeeDaoJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {

		// create the query
		Query<Employee> query = (Query<Employee>) entityManager.createQuery("from Employee");

		// execute the query
		List<Employee> employees = query.getResultList();

		// return the result
		return employees;
	}

	@Override
	public Employee findById(int theId) {

		// get the employee
		Employee employee = entityManager.find(Employee.class, theId);

		// return the employee
		return employee;
	}

	@Override
	public void save(Employee theEmployee) {

		// save the employee
		Employee dbEmployee=entityManager.merge(theEmployee);;
		
		
		theEmployee.setId(dbEmployee.getId());
	}

	@Override
	public void deleteById(int theId) {

		// create the query for deleting the employee
		Query query=(Query) entityManager.createQuery("delete from Employee where id=:employeeId");
		query.setParameter("employeeId", theId);
		
		//execute the query
		query.executeUpdate();

	}

}
