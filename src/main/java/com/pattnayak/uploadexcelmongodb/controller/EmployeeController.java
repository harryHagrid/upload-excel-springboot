package com.pattnayak.uploadexcelmongodb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pattnayak.uploadexcelmongodb.entity.Employee;
import com.pattnayak.uploadexcelmongodb.exceptions.EmployeeServiceException;
import com.pattnayak.uploadexcelmongodb.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@PostMapping("/save")
	public ResponseEntity<Map<String, Object>> saveEmployee(@RequestBody Employee emp){
		
		Map<String, Object> response = new HashMap<>();
		log.info("API /save called successfully..");
		try {
			
			response.put("body", this.employeeService.addEmployee(emp));
			response.put("status", HttpStatus.CREATED);
			response.put("success", true);
			response.put("error", false);
			
			return ResponseEntity.status(HttpStatus.OK)
					.header("status", String.valueOf(HttpStatus.OK))
					.body(response);
		} catch (EmployeeServiceException e) {
			
			log.error("exception message - "+e.getMessage());
			log.error("exception cause - "+e.getCause());
			
			response.put("body", e.getMessage());
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("exception", e.getCause());
			response.put("success",false);
			response.put("error", true);
			return ResponseEntity.status(HttpStatus.OK)
					.header("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
					.body(response);
		}
		
		
		
	}
	
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Map<String, Object>> getEmployee(@PathVariable String id){
		
		Map<String, Object> response = new HashMap<>();
		log.info("API /employee/get/"+ id+" called successfully..");
		try {
			
			response.put("body", this.employeeService.getEmployee(id));
			response.put("status", HttpStatus.FOUND);
			response.put("success", true);
			response.put("error", false);
			
			return ResponseEntity.status(HttpStatus.OK)
					.header("status", String.valueOf(HttpStatus.OK))
					.body(response);
		} catch (EmployeeServiceException e) {
			
			log.error("exception message - "+e.getMessage());
			log.error("exception cause - "+e.getCause());
			
			response.put("body", e.getMessage());
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("exception", e.getCause());
			response.put("success",false);
			response.put("error", true);
			return ResponseEntity.status(HttpStatus.OK)
					.header("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
					.body(response);
		}
		
		
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<Map<String, Object>> getAllEmployees(){
		
		Map<String, Object> response = new HashMap<>();
		log.info("API /employee/all called successfully..");
		try {
			
			response.put("body", this.employeeService.getAllEmployees());
			response.put("status", HttpStatus.FOUND);
			response.put("success", true);
			response.put("error", false);
			
			return ResponseEntity.status(HttpStatus.OK)
					.header("status", String.valueOf(HttpStatus.OK))
					.body(response);
		} catch (EmployeeServiceException e) {
			
			log.error("exception message - "+e.getMessage());
			log.error("exception cause - "+e.getCause());
			
			response.put("body", e.getMessage());
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("exception", e.getCause());
			response.put("success",false);
			response.put("error", true);
			return ResponseEntity.status(HttpStatus.OK)
					.header("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
					.body(response);
		}
		
		
		
	}

}
