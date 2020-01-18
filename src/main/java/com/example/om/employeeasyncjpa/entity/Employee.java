package com.example.om.employeeasyncjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "EMP")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemidgenerator")
    @SequenceGenerator(name = "itemidgenerator", sequenceName = "EMPNO_SEQUENCE", allocationSize = 1)
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
