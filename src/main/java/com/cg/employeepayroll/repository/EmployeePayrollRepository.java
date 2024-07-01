package com.cg.employeepayroll.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.employeepayroll.model.EmployeePayrollData;

@Repository
public interface EmployeePayrollRepository extends JpaRepository<EmployeePayrollData,Long>{
	
}
