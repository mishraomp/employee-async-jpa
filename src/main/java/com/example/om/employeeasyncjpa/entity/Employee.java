package com.example.om.employeeasyncjpa.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "EMP")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeIdGenerator")
    @SequenceGenerator(name = "employeeIdGenerator", sequenceName = "EMPNO_SEQUENCE", allocationSize = 1)
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
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Address.class)
    private List<Address> addresses;
}
