package com.example.om.employeeasyncjpa.service;

import com.example.om.employeeasyncjpa.entity.Employee;
import com.example.om.employeeasyncjpa.repository.EmployeeRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static lombok.AccessLevel.PRIVATE;

@Service
@Slf4j
public class EmployeeService {


    @Getter(PRIVATE)
    private final EmployeeRepository repository;

    public EmployeeService(@Autowired final EmployeeRepository repository) {
        this.repository = repository;
    }

    @Async
    @Transactional(propagation = Propagation.SUPPORTS)
    public CompletableFuture<List<Employee>> findAll() {
        try {
            List<Employee> employees = new ArrayList<>();
            getRepository().findAll().forEach(employees::add);
            return CompletableFuture.completedFuture(employees);
        } catch (final Exception ex) {
            throw new CompletionException(ex);
        }
    }

    @Async
    @Transactional(propagation = Propagation.SUPPORTS)
    public CompletableFuture<ResponseEntity<Employee>> findById(final Integer employeeId) {
        try {
            val result = getRepository().findById(employeeId);
            return result.map(employee -> CompletableFuture.completedFuture(ResponseEntity.ok(employee))).orElseGet(() -> CompletableFuture.completedFuture(ResponseEntity.notFound().build()));
        } catch (final Exception ex) {
            log.error("Error occurred during findById :: " + employeeId, ex);
            throw new CompletionException(ex);
        }
    }

    @Async
    @Transactional
    public CompletableFuture<ResponseEntity<Employee>> save(final Employee employee) {
        try {
            val result = getRepository().save(employee);
            return CompletableFuture.completedFuture(ResponseEntity.status(201).body(result));
        } catch (final Exception ex) {
            throw new CompletionException(ex);
        }

    }

    @Async
    @Transactional
    public CompletableFuture<ResponseEntity<Employee>> update(final Integer employeeId, final Employee employee) {

        try {
            val result = getRepository().findById(employeeId);
            if (result.isPresent()) {
                val id = result.get().getEmployeeId();
                BeanUtils.copyProperties(employee, result.get());
                result.get().setEmployeeId(id);
                val e = getRepository().save(result.get());
                return CompletableFuture.completedFuture(ResponseEntity.ok(e));
            } else {
                return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
            }
        } catch (final Exception ex) {
            throw new CompletionException(ex);
        }
    }

    @Async
    @Transactional
    public CompletableFuture<ResponseEntity<Void>> delete(final Integer employeeId) {

        try {
            val result = getRepository().findById(employeeId);
            if (result.isPresent()) {
                getRepository().delete(result.get());
                return CompletableFuture.completedFuture(ResponseEntity.ok().build());
            } else {
                return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
            }
        } catch (final Exception ex) {
            throw new CompletionException(ex);
        }
    }
}
