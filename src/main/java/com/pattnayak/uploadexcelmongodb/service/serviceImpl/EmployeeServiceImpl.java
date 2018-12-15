package com.pattnayak.uploadexcelmongodb.service.serviceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pattnayak.uploadexcelmongodb.entity.Employee;
import com.pattnayak.uploadexcelmongodb.exceptions.EmployeeServiceException;
import com.pattnayak.uploadexcelmongodb.repository.EmployeeRepository;
import com.pattnayak.uploadexcelmongodb.service.EmployeeService;
import com.pattnayak.uploadexcelmongodb.utilities.ReadUserDetailFromExcel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;
	
	private List<Employee> employeesDataFromExcel = new ArrayList<>();

	@Override
	public String addEmployee(Employee employee) throws EmployeeServiceException {

		String message;
		ZoneId z = ZoneId.of("Asia/Kolkata");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), z);

		log.info("Add employee method called...");
		try {
			employee.setCreatedOn(zonedDateTime.toLocalDateTime());
			this.employeeRepo.save(employee);
			log.info("Employee saved succesfully - " + employee.getName());
			message = "Employee saved succesfully - " + employee.getName();
		} catch (DataAccessException e) {
			log.error("Unable to save employee");
			log.error("Exception occured- " + e.getCause());
			message = "Unable to save employee - " + employee.getName();
			throw new EmployeeServiceException("Unable to save employee", e);
		} finally {

		}

		return message;
	}

	@Override
	public List<Employee> getAllEmployees() throws EmployeeServiceException {
		log.info("Get All employees method called....");

		try {

			log.info("Employees fetched successfully. Employees count = " + this.employeeRepo.findAll().size());

			return this.employeeRepo.findAll();

		} catch (DataAccessException e) {

			log.error("Unable to fetch all employees.");
			log.error("Exception occured- " + e.getCause());
			throw new EmployeeServiceException("Unable to fetch all employees", e);
		}

	}

	@Override
	public Employee getEmployee(String id) throws EmployeeServiceException {

		log.info("Get employee method called ... ");

		try {
			return this.employeeRepo.findOne(id);
		} catch (DataAccessException e) {
			log.error("Unable to get employee by id - " + id);
			log.error("exception occured - " + e.getCause());
			throw new EmployeeServiceException("Unable to get employee by id - " + id, e);
		}

	}

	@Override
	public List<Employee> processEmployeeDataFromExcel(MultipartFile file) throws EmployeeServiceException {
		
		ReadUserDetailFromExcel readData = new ReadUserDetailFromExcel();
		
		List<Employee> employees = new ArrayList<Employee>();
		
		try {
			readData.readUsersDataFromExcel(file);
			employees = readData.getEmployeeDetails();
			this.employeesDataFromExcel = employees;
			return employees;
		}catch(IOException e) {
			throw new EmployeeServiceException("Unable to read data from excel", e);
		}catch(InvalidFormatException e) {
			throw new EmployeeServiceException("Unable to read data from excel", e);
		}
		
		
	}

	@Override
	public String saveEmployeesFromExcel() throws EmployeeServiceException {
		
		for(Employee emp : this.employeesDataFromExcel) {
			
			this.addEmployee(emp);
		}
		
		return "Employee Saved successfully. Number of records - "+this.employeesDataFromExcel.size();
	}

}
