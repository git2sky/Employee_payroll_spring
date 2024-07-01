package com.cg.employeepayroll.employeepayrollcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.employeepayroll.dto.EmployeePayrollDTO;
import com.cg.employeepayroll.exceptions.EmployeePayrollException;
import com.cg.employeepayroll.services.IEmployeePayrollService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Controller to all API calls")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001" },allowedHeaders = "*")
@RestController
@RequestMapping("/employeepayroll")
public class EmployeePayrollController {
	
	@Autowired
	private IEmployeePayrollService employeePayrollService;
	
	@ApiOperation(value = "API to get all data")
	@GetMapping("/get")
	public ResponseEntity<List<EmployeePayrollDTO>> getAllUser(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeePayrollService.getAllUser());
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@ApiOperation(value = "API to get data based on id")
	@GetMapping("/get/{id}")
	public ResponseEntity<EmployeePayrollDTO> getUserUsingId(@PathVariable Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeePayrollService.getUserById(id));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	 
	@ApiOperation(value = "API  to create new data")
	@PostMapping("/create")
	public ResponseEntity<EmployeePayrollDTO> createUser(@Valid @RequestBody EmployeePayrollDTO user){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(employeePayrollService.createUser(user));
		}catch(EmployeePayrollException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@ApiOperation(value = "API to update existing data")
	@PutMapping("/update/{id}")
	public ResponseEntity<EmployeePayrollDTO> updateUser(@PathVariable Long id,
			@Valid @RequestBody EmployeePayrollDTO user){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeePayrollService.updateUser(id,user));
		}catch(EmployeePayrollException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@ApiOperation(value = "API to delete data")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<EmployeePayrollDTO> deleteUser(@PathVariable("id") Long id){
		try {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeePayrollService.deleteUser(id));
		}catch(EmployeePayrollException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
}
