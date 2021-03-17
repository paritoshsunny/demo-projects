	package com.sunny.springboot.cruddemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sunny.springboot.cruddemo.entity.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {
	
	
	@Query("SELECT e FROM Employee e WHERE CONCAT(e.id, ' ', e.firstName, ' ', e.lastName, ' ', e.email) LIKE %?1%")
	public List<Employee> search(String keyword);

	

	
	

}
