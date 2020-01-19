package com.example.om.employeeasyncjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ADDRESS_TYPE")
public class AddressType {

    @Id
    @Column(name = "ADDRESS_TYPE_CODE", nullable = false, insertable = false, updatable = false)
    private Integer addressTypeCode;
    @Column(name = "ADDRESS_TYPE_DESCRIPTION", nullable = false, insertable = false, updatable = false)
    private String addressTypeDescription;
    @OneToMany(mappedBy = "addressType")
    private List<Address> addresses;
}

enum AddressTypeCode {
    HOME,
    OFFICE
}