package com.completableFuture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.completableFuture.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    Object findAllById(int id);
}
