package com.sunny.springboot.cruddemo.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

	// expose PUT mapping "/employees" to update the existing employee
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {

		employeeService.save(theEmployee);

		return theEmployee;
	}
	
	
	// expose DELETE mapping "/employees/{employeeId}" to delete the existing employee
	
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee employee=employeeService.findById(employeeId);
		if(employee==null) {
			throw new RuntimeException("Employee Id " + employeeId + " not found");
		}
		
		employeeService.deleteById(employeeId);
		return  "Deleted Employee Id is : "+employeeId;
	}
	
	
	@RequestMapping("user")
	@ResponseBody
	public Principal user(Principal principal) {
		return principal;
	}
	

}
