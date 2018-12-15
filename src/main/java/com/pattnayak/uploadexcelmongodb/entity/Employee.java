package com.pattnayak.uploadexcelmongodb.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "employee")
@Getter
@Setter
public class Employee {

	@Id
	private String id;

	@Pattern(regexp = "[M]{1}[1-9]{1}[0-9]{6}", message = "MID first digit must be M, second digit must be between 1-9, and last 6 digits must be between 0-9.")
	@Indexed(unique=true)
	private String mid;

	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
	@Pattern(regexp = "[a-zA-Z ]+", message = "Name can only have uppercase and lowercase letters.")

	private String name;
	@Size(min = 3, max = 120, message = "Description can be between 3 and 120 characters.")
	private String description;

	@Pattern(regexp = "[a-zA-Z0-9.]+", message = "Invalid email regex pattern.")
	@Size(min = 3, max = 30, message = "Email base should be 3 to 30 characters.")
	private String email;

	private String displayName;

	private String status;

	private String timeZone;

	private String gender;

	@Size(min = 3, max = 30)
	@Pattern(regexp = "[a-zA-Z ]+", message = "Locale can't have any special characters.")
	private String locale;
	private String institute;
	private String city;
	private String state;
	private String region;
	private String qualification;
	private String specialization;

	// Metadata
	private String createdBy;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDateTime createdOn;

	private String updatedBy;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDateTime updatedOn;
	@Override
	public String toString() {
		return "Employee [mid=" + mid + ", name=" + name + "]";
	}
	
	

}
