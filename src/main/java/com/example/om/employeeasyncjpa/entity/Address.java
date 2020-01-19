package com.example.om.employeeasyncjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id
    @Column(name = "ADDRESS_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressIdGenerator")
    @SequenceGenerator(name = "addressIdGenerator", sequenceName = "ADDRESS_SEQUENCE", allocationSize = 1)
    private Integer addressId;

    @ManyToOne
    @JoinColumn(name = "ADDRESS_TYPE", referencedColumnName = "ADDRESS_TYPE_CODE")
    private AddressType addressType;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, optional = false, targetEntity = Employee.class)
    @JoinColumn(name = "EMPNO", referencedColumnName = "EMPNO")
    private Employee employee;
    @Column(name = "ADDRESS_1")
    private String addressLine1;
    @Column(name = "ADDRESS_2")
    private String addressLine2;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STATE")
    private String state;
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    @Column(name = "COUNTRY")
    private String country;
}
