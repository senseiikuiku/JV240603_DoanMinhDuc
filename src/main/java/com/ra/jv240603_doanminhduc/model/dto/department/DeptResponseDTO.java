package com.ra.jv240603_doanminhduc.model.dto.department;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class DeptResponseDTO {
    private int id;
    private String deptName;
    private String deptDescription;

    private String deptStatus;
}
