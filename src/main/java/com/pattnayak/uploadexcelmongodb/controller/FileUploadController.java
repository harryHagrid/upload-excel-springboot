package com.pattnayak.uploadexcelmongodb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pattnayak.uploadexcelmongodb.exceptions.EmployeeServiceException;
import com.pattnayak.uploadexcelmongodb.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/upload")
@Slf4j
public class FileUploadController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employee/excel")
	public ResponseEntity<Map<String, Object>> uploadEmployeesExcel(@RequestParam("file") MultipartFile file){
		
		log.info("UploadEmployee got called ...");
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			this.employeeService.processEmployeeDataFromExcel(file);
			this.employeeService.saveEmployeesFromExcel();
			String message = this.employeeService.saveEmployeesFromExcel();
			response.put("success", true);
			response.put("status", HttpStatus.OK);		
			response.put("body", message);
			response.put("error",false);
			return ResponseEntity.status(HttpStatus.OK)
					.header("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
					.body(response);
		} catch (EmployeeServiceException e) {
			
			log.error("exception occured - "+e.getMessage());
			log.error("cause - "+e.getCause());
			response.put("success", false);
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);		
			response.put("body", null);
			response.put("error",true);
			response.put("exception", e.getLocalizedMessage());
			response.put("message", e.getMessage());
			response.put("cause", e.getCause().getMessage());
			return ResponseEntity.status(HttpStatus.OK)
					.header("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
					.body(response);
		}
		
		
	}

}
