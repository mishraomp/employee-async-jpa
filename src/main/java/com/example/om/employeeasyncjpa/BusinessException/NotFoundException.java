package com.example.om.employeeasyncjpa.BusinessException;

import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotFoundException extends Exception {
    private int code;
    private String message;
    private Exception ex;
}
