package com.example.om.employeeasyncjpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "EMP")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "EMPNO")
    private Integer employeeId;
    @Column(name = "ENAME")
    private String employeeName;
    @Column(name = "JOB")
    private String job;
    @Column(name = "MGR")
    private String manager;
    @Column(name = "HIREDATE")
    private Date hireDate;
    @Column(name = "SAL")
    private Integer salary;
    @Column(name = "COMM")
    private Integer comm;
    @Column(name = "DEPTNO")
    private String departmentNum;
}
