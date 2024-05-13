package com.completableFuture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.completableFuture.model.Employee;
import com.completableFuture.service.EmployeeService;

@RestController
@RequestMapping("/insert")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	@PostMapping("/data")
	public ResponseEntity<String> insertEmployee(@RequestBody List<Employee> employee){
	ResponseEntity<String> ad =	empService.addData(employee);
		
		return ad;
		
	}

	@GetMapping("/fetch/{id}")
	public ResponseEntity<Employee> pickEmployee(@PathVariable Integer id){
		Employee e =empService.pickEmp(id);
		return ResponseEntity.ok(e);


	}
}
