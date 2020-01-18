package com.example.om.employeeasyncjpa.rest.server;

import com.example.om.employeeasyncjpa.entity.Employee;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequestMapping(path = "/api/v1/employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public interface IEmployeeEndpoint {

    @GetMapping
    CompletableFuture<List<Employee>> findAll();

    @GetMapping(path = "/{id}")
    CompletableFuture<ResponseEntity<Employee>> findById(@PathVariable Integer id);

    @PostMapping
    CompletableFuture<ResponseEntity<Employee>> save(@RequestBody Employee employee);

    @PutMapping(path = "/{id}")
    CompletableFuture<ResponseEntity<Employee>> update(@PathVariable Integer id, @RequestBody Employee employee);

    @DeleteMapping(path = "/{id}")
    CompletableFuture<ResponseEntity<Void>> delete(@PathVariable Integer id);
}
