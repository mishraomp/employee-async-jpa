package com.example.om.employeeasyncjpa.rest.server;

import com.example.om.employeeasyncjpa.entity.Employee;
import com.example.om.employeeasyncjpa.service.EmployeeService;
import lombok.Getter;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static lombok.AccessLevel.PRIVATE;

@RestController
public class EmployeeEndpointImpl implements IEmployeeEndpoint {

    @Getter(PRIVATE)
    private final EmployeeService service;

    public EmployeeEndpointImpl(@Autowired final EmployeeService service) {
        this.service = service;
    }

    @Override
    public CompletableFuture<List<Employee>> findAll() {
        val result = getService().findAll();
        return result;
    }
}
