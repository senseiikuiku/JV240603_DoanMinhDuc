package com.ra.jv240603_doanminhduc.model.dto.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ra.jv240603_doanminhduc.model.entity.Department;
import com.ra.jv240603_doanminhduc.validate.EmpUnique;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class EmpRequestDTO {
    private String empName;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private Date birthOfDate;
    private boolean empSex;
    private String empAddress;
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "Không đúng định dạng email")
    @EmpUnique(message = "Email da ton tai")
    private String empEmail;
    @Pattern(regexp = "^(0[3|5|7|8|9])+([0-9]{8})$",
            message = "Không đúng định dạng số điện thoại")
    @EmpUnique(message = "Số điện thoại da ton tai")
    private String empPhone;
    private String empAvatar;
    private boolean empStatus;
    private Integer department;

}
