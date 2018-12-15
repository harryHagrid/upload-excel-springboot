package com.pattnayak.uploadexcelmongodb.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pattnayak.uploadexcelmongodb.entity.Employee;
import com.pattnayak.uploadexcelmongodb.exceptions.EmployeeServiceException;

public interface EmployeeService {
	
	String addEmployee(Employee employee) throws EmployeeServiceException;
	
	List<Employee> getAllEmployees() throws EmployeeServiceException;
	
	Employee getEmployee(String id) throws EmployeeServiceException;
	
	List<Employee> processEmployeeDataFromExcel(MultipartFile file) throws EmployeeServiceException;
	
	String saveEmployeesFromExcel() throws EmployeeServiceException;

}
