package com.example.om.employeeasyncjpa.rest.server;

import com.example.om.employeeasyncjpa.entity.Employee;
import com.example.om.employeeasyncjpa.service.EmployeeService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return getService().findAll();
    }

    @Override
    public CompletableFuture<ResponseEntity<Employee>> findById(final Integer id) {
        return getService().findById(id);
    }

    @Override
    public CompletableFuture<ResponseEntity<Employee>> save(final Employee employee) {
        return getService().save(employee);
    }

    @Override
    public CompletableFuture<ResponseEntity<Employee>> update(final Integer id, final Employee employee) {
        return getService().update(id, employee);
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> delete(final Integer id) {
        return getService().delete(id);
    }
}
