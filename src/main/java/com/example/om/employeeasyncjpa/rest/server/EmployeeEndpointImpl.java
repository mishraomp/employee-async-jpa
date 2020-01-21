package com.example.om.employeeasyncjpa.rest.server;

import com.example.om.employeeasyncjpa.entity.Employee;
import com.example.om.employeeasyncjpa.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public CompletableFuture<Page<Employee>> findAll(final Integer pageNumber, final Integer pageSize, final String sortCriteriaJson) {
        final ObjectMapper mapper = new ObjectMapper();
        final List<Sort.Order> sorts = new ArrayList<>();
        try {
            if (StringUtils.isNotBlank(sortCriteriaJson)) {
                Map<String, String> sortMap = mapper.readValue(sortCriteriaJson, new TypeReference<Map<String, String>>() {
                });

                sortMap.forEach((k, v) -> {
                    if ("ASC".equalsIgnoreCase(v)) {
                        sorts.add(new Sort.Order(Sort.Direction.ASC, k));
                    } else {
                        sorts.add(new Sort.Order(Sort.Direction.DESC, k));
                    }
                });
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return getService().findAll(pageNumber, pageSize, sorts);
    }

    @Override
    public CompletableFuture<ResponseEntity<List<Employee>>> findByName(final String employeeName) {
        return getService().findByName(employeeName);
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
