package com.example.om.employeeasyncjpa.rest.server;

import com.example.om.employeeasyncjpa.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequestMapping(path = "/api/v1/employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public interface IEmployeeEndpoint {

    @CrossOrigin
    @GetMapping
    CompletableFuture<Page<Employee>> findAll(@RequestParam(defaultValue = "0") Integer pageNumber,
                                              @RequestParam(defaultValue = "10") Integer pageSize,
                                              @RequestParam(defaultValue = "") String sortCriteriaJson);

    @CrossOrigin
    @GetMapping(path = "/name")
    CompletableFuture<ResponseEntity<List<Employee>>> findByName(@Param("employeeName") String employeeName);

    @GetMapping(path = "/{id}")
    @CrossOrigin
    CompletableFuture<ResponseEntity<Employee>> findById(@PathVariable Integer id);

    @PostMapping
    @CrossOrigin
    CompletableFuture<ResponseEntity<Employee>> save(@RequestBody Employee employee);

    @PutMapping(path = "/{id}")
    @CrossOrigin
    CompletableFuture<ResponseEntity<Employee>> update(@PathVariable Integer id, @RequestBody Employee employee);

    @DeleteMapping(path = "/{id}")
    @CrossOrigin
    CompletableFuture<ResponseEntity<Void>> delete(@PathVariable Integer id);
}
