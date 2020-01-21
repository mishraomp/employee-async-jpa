package com.example.om.employeeasyncjpa.repository;

import com.example.om.employeeasyncjpa.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e WHERE e.employeeName= ?1")
    List<Employee> findEmployeeByEmployeeName(String employeeName);
}
