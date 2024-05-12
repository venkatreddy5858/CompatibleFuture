package com.completableFuture.dao;

import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.completableFuture.model.Employee;

import java.util.UUID;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Id>{
	

}
