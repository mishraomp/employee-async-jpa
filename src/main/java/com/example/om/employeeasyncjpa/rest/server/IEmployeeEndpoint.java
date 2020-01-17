package com.example.om.employeeasyncjpa.rest.server;

import com.example.om.employeeasyncjpa.entity.Employee;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequestMapping(path = "/api/v1/employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public interface IEmployeeEndpoint {

    @GetMapping
    CompletableFuture<List<Employee>> findAll();
}
