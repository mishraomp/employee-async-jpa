package com.example.om.employeeasyncjpa;

import com.example.om.employeeasyncjpa.entity.Employee;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;

import static io.restassured.RestAssured.given;

//@SpringBootTest
class EmployeeAsyncJpaApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testEmployeeSave(){
		val list = new ArrayList<Employee>();
		for(int i=1001;i<50000;i++){
			list.add(Employee.builder().employeeName("om"+i).comm(i).salary(i).departmentNum(i+"").hireDate(new Date()).job("job"+i).manager("manager"+i).build());
		}
		list.forEach(employee -> {
			given().contentType("application/json").body(employee).when().post("http://localhost:8092/api/v1/employee").then().statusCode(201);
		});
	}

	@Test
	void testEmployeeGetAll(){
		val list = new ArrayList<Employee>();
		for(int i=0;i<100;i++){
			list.add(Employee.builder().employeeName("om"+i).comm(i).salary(i).departmentNum(i+"").hireDate(new Date()).job("job"+i).manager("manager"+i).build());
		}
		list.parallelStream().forEach(employee -> {
			given().contentType("application/json").when().get("http://localhost:8092/api/v1/employee").then().statusCode(200);
		});
	}
}
