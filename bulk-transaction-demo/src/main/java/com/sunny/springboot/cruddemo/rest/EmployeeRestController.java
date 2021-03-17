package com.sunny.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunny.springboot.cruddemo.entity.Employee;
import com.sunny.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;

	// inject Employee DAO

	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		this.employeeService = theEmployeeService;
	}

	// expose "/employees" and return list of employees

	@GetMapping("/employees")
	public List<Employee> findAll() {

		return employeeService.findAll();

	}

//----------------------------------------------------------------------------------------------------------------
	// pagination and sorting support by extending repository to PagingAndSortingRepository
//----------------------------------------------------------------------------------------------------------------
	@GetMapping("/employees/all")
	Page<Employee> getAllEmployee(Pageable pageable) {
		return employeeService.findAll(pageable);

	}

// ----------------------------------------------------------------------------------------------------------------
	// searching database with a keyword by adding a method with a query in DAO layer
// ----------------------------------------------------------------------------------------------------------------

	@GetMapping("/employees/search/{keyword}")
	List<Employee> searchEmployee(@PathVariable String keyword) {
		return employeeService.listAll(keyword);

	}

	// expose "/employees/{employeeId}" to get employee by id

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {

		Employee theEmployee = employeeService.findById(employeeId);

		if (theEmployee == null) {
			throw new RuntimeException("Employee Id " + employeeId + " not found");
		}

		return theEmployee;
	}

	// expose POST mapping "/employees" to add new employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {

		theEmployee.setId(0); // just incase user enter the id in the json

		employeeService.save(theEmployee);

		return theEmployee;

	}

//----------------------------------------------------------------------------------------------------------------	
	// insert 200 employees in the database
//----------------------------------------------------------------------------------------------------------------	
	@PostMapping("/insert")
	public String insertEmployees() {
		// List<Employee> employees= new ArrayList<Employee>();
		int i = 0;
		while (i < 200) {
			Employee e = new Employee("sam" + i, "thomas" + i, "sam" + i + "@gmail.com");
			employeeService.save(e);
			i++;
		}

		return "200 Employees added to the database";
	}

	// expose PUT mapping "/employees" to update the existing employee
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {

		employeeService.save(theEmployee);

		return theEmployee;
	}

	// expose DELETE mapping "/employees/{employeeId}" to delete the existing
	// employee

	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee employee = employeeService.findById(employeeId);
		if (employee == null) {
			throw new RuntimeException("Employee Id " + employeeId + " not found");
		}

		employeeService.deleteById(employeeId);
		return "Deleted Employee Id is : " + employeeId;
	}

}
