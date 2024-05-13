package com.completableFuture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.completableFuture.model.Employee;
import com.completableFuture.repository.EmployeeRepo;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepo eprepo;
	
	
	
	public ResponseEntity<String> addData(List<Employee> empList){
		empList.parallelStream().forEach(aj -> eprepo.save(aj));
		return ResponseEntity.ok("success");
		
	}

    public Employee pickEmp(int id) {
		 Employee e = (Employee) eprepo.findAllById(id);

		 return e;

    }
}
