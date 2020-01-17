package com.example.om.employeeasyncjpa.service;

import com.example.om.employeeasyncjpa.entity.Employee;
import com.example.om.employeeasyncjpa.repository.EmployeeRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static lombok.AccessLevel.PRIVATE;

@Service
public class EmployeeService {


    @Getter(PRIVATE)
    private final EmployeeRepository repository;

    public EmployeeService(@Autowired final EmployeeRepository repository) {
        this.repository = repository;
    }

    @Async
    public CompletableFuture<List<Employee>> findAll() {
        List<Employee> employees = new ArrayList<>();
        getRepository().findAll().forEach(employees::add);
        return CompletableFuture.completedFuture(employees);
    }
}
