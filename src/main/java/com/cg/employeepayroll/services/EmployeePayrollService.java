package com.cg.employeepayroll.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.employeepayroll.dto.EmployeePayrollDTO;
import com.cg.employeepayroll.exceptions.EmployeePayrollException;
import com.cg.employeepayroll.model.EmployeePayrollData;
import com.cg.employeepayroll.repository.EmployeePayrollRepository;

@Service
public class EmployeePayrollService implements IEmployeePayrollService{	
	@Autowired
    private EmployeePayrollRepository employeePayrollRepository;
	
	@Override
	public List<EmployeePayrollDTO> getAllUser(){
		return employeePayrollRepository.findAll().stream()
				.map(employeePayroll -> new EmployeePayrollDTO(employeePayroll))
				.collect(Collectors.toList());
    }
	
	@Override
	public EmployeePayrollDTO getUserById(Long id) {
		EmployeePayrollData employee = employeePayrollRepository.findById(id).orElseThrow( () -> {
			throw new EmployeePayrollException("User not found");
		});
		return new EmployeePayrollDTO(employee);
	}
	
	@Override
	public EmployeePayrollDTO createUser(EmployeePayrollDTO employeePayrollDTO) {
		EmployeePayrollData employeePayroll = new EmployeePayrollData(employeePayrollDTO);
		return new EmployeePayrollDTO(employeePayrollRepository.save(employeePayroll));
	}
	
	@Override
	public EmployeePayrollDTO updateUser(Long id, EmployeePayrollDTO employeePayrollDTO) {
		EmployeePayrollData employee = new EmployeePayrollData(this.getUserById(id));
		employee.setName(employeePayrollDTO.getName());
		employee.setProfilePic(employeePayrollDTO.getProfilePic());
		employee.setGender(employeePayrollDTO.getGender());
		employee.setDepartments(employeePayrollDTO.getDepartments());
		employee.setSalary(employeePayrollDTO.getSalary());
		employee.setDepartments(employeePayrollDTO.getDepartments());
		employee.setNote(employeePayrollDTO.getNote());
		return new EmployeePayrollDTO(employee);
	}
	
	@Override
	public EmployeePayrollDTO deleteUser(Long id) {
//		return employeePayrollRepository.findById(id).map(employeePayroll -> {
//			employeePayrollRepository.deleteById(employeePayroll.getId());
//			return new EmployeePayrollDTO(employeePayroll);
//		}).orElseThrow(() -> new EmployeePayrollException("User Not Found"));
		EmployeePayrollData employee = employeePayrollRepository.findById(id).orElseThrow( () -> new EmployeePayrollException("user not found"));
		employeePayrollRepository.deleteById(employee.getId());
		return new EmployeePayrollDTO(employee);
 	}
}
