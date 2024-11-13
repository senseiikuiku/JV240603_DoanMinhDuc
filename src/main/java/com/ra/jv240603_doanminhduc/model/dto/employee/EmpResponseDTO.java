package com.ra.jv240603_doanminhduc.model.dto.employee;

import com.ra.jv240603_doanminhduc.model.entity.Department;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class EmpResponseDTO {
    private int id;
    private String empName;
    private Date birthOfDate;
    private String empSex;
    private String empAddress;
    private String empEmail;
    private String empPhone;
    private String empAvatar;
    private String empStatus;
    private Department department;
}
