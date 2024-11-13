package com.ra.jv240603_doanminhduc.model.dto.employee;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class EmpUpdateRequestDTO {
    private int id;
    private String empName;
    private Date birthOfDate;
    private boolean empSex;
    private String empAddress;
    private String empEmail;
    private String empPhone;
    private String empAvatar;
    private boolean empStatus;
    private Integer department;
            ;
}
