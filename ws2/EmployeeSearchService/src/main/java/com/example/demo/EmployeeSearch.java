package com.example.demo;

import java.util.List;

public interface EmployeeSearch {
	
	public Employee getEmployeeById(int id);
	public String addEmployee(Employee emp);
	public Employee updateEmployee(Employee emp);
	public String removeEmployeeById(int id);
	public List<Employee> getAllEmployee();

}
