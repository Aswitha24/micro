//package com.example.demo;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class EmployeeController {
//	@Autowired
//	EmployeeRepository employeeRepository;
//
//	@PostMapping("/add")
//	public String addEmployee(@RequestBody Employee emp)
//	{
//		employeeRepository.save(emp);
//		return "SUCCESSW";
//	}
//	@GetMapping("/read/{id}")
//	public Employee readEmployee(@PathVariable int id)
//	{
//		return employeeRepository.findById(id).get();
//	}
//	@GetMapping("/readall")
//	public List<Employee> readAllEmployee()
//	{
//		return employeeRepository.findAll();
//	}
//}


package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope
public class EmployeeController {
	
	@Autowired
	EmployeeSearch employeeSearch;
	
	@Value("${author.name: Sunil default}")
	private String author;
	
	@GetMapping("/author")
	public String getAuthor()
	{
		return author;
	}
	
	@CrossOrigin
	@GetMapping("/employee/{id}")
	public Employee findById(@PathVariable int id)
	{
		return employeeSearch.getEmployeeById(id);
	}
	
	@CrossOrigin	
	@GetMapping("/employee")
	public List<Employee> getAll()
	{
		return employeeSearch.getAllEmployee();
	}
	
	@CrossOrigin
	@PostMapping("/employee")
	public Response addEmployee(@RequestBody Employee emp)
	{
		employeeSearch.addEmployee(emp);
		Response response = new Response();
		response.setResult("Employee Addedd Successfully");
		return response;
				
	}
	
	@CrossOrigin
	@DeleteMapping("/employee/{id}")
	public Response removeEmployeeById(@PathVariable int id)
	{
		String resp= employeeSearch.removeEmployeeById(id);
		if (resp.equals("deleted")) {
			Response response = new Response();
			response.setResult("Employee Deleted");
			return response;
		} else
		{
			Response response = new Response();
			response.setResult("Error in Deleting");
			return response;
		}
	}
	
	@CrossOrigin
	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@PathVariable int id,@RequestBody Employee emp)
	{
		return employeeSearch.updateEmployee(emp);
	}
}

