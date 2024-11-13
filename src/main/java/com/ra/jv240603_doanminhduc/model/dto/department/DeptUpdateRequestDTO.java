package com.ra.jv240603_doanminhduc.model.dto.department;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class DeptUpdateRequestDTO {
    private int id;
    private String deptName;
    private String deptDescription;
    private boolean deptStatus;
}
