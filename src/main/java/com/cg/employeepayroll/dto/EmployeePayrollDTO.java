package com.cg.employeepayroll.dto;

import com.cg.employeepayroll.model.EmployeePayrollData;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class EmployeePayrollDTO {
	
	private Long id;
	
	@NotEmpty(message="Employee name cannot be null")
	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message="Employee name Invalid")
	private String name;
	
	@Min(value=500,message="Min Wage should be more than 500")
	@Pattern(regexp = "[0-9]+", message ="salary should be a number")
	private String salary;
	
	@JsonFormat(pattern = "dd MMM yyyy")
	@NotNull(message="startDate should not be empty")
	@PastOrPresent(message="startDate should be past or todays date")
	private LocalDate startDate;
	
	@Pattern(regexp="male|female",message="gender should be either male or female")
	private String gender;
	
	@NotBlank(message="Note cannot be empty")
	private String note;
	
	@NotBlank(message="profilePic cannot be empty")
	private String profilePic;
	
	@NotNull(message="department should not be empty")
	private List<String> departments;
	
	public EmployeePayrollDTO(EmployeePayrollData employeePayroll) {
		this.setId(employeePayroll.getId());
		this.setName(employeePayroll.getName());
		this.setSalary(employeePayroll.getSalary());
		this.setDepartments(employeePayroll.getDepartments());
		this.setGender(employeePayroll.getGender());
		this.setNote(employeePayroll.getNote());
		this.setProfilePic(employeePayroll.getProfilePic());
		this.setStartDate(employeePayroll.getStartDate());
	}
	
	public EmployeePayrollDTO() {}
	
}
