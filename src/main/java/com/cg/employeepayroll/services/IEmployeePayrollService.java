package com.cg.employeepayroll.services;

import java.util.List;

import com.cg.employeepayroll.dto.EmployeePayrollDTO;
import com.cg.employeepayroll.model.EmployeePayrollData;

public interface IEmployeePayrollService {
	List<EmployeePayrollDTO> getAllUser();

	EmployeePayrollDTO createUser(EmployeePayrollDTO user);

	EmployeePayrollDTO updateUser(Long id,EmployeePayrollDTO user);

	EmployeePayrollDTO deleteUser(Long id);

	EmployeePayrollDTO getUserById(Long id);
}
