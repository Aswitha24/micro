package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EmployeeClientController {

	@Autowired
	RestTemplate restTemplate;
	@PostMapping("/addemployee")
	public String addEmployee(@RequestBody Employee emp)
	{
		String url="http://localhost:5678/add";
		
	ResponseEntity<String> str=	restTemplate.postForEntity(url, emp, String.class);
		return str.getBody() ;
	}
	@GetMapping("/getbyid/{id}")
	public Employee Employee(@PathVariable int id)
	{
		String url="http://localhost:5678/read/"+id;
		
ResponseEntity<Employee> e=	restTemplate.getForEntity(url, Employee.class);
return e.getBody();
	}
	
}
