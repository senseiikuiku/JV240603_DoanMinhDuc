package com.ra.jv240603_doanminhduc.service.employee;


import com.ra.jv240603_doanminhduc.model.dto.employee.EmpRequestDTO;
import com.ra.jv240603_doanminhduc.model.dto.employee.EmpResponseDTO;
import com.ra.jv240603_doanminhduc.model.dto.employee.EmpUpdateRequestDTO;

import java.util.List;

public interface EmpService {
    List<EmpResponseDTO> findAll();
    EmpResponseDTO create(EmpRequestDTO empRequestDTO);
    EmpResponseDTO findById(Integer id);
    EmpResponseDTO update(EmpUpdateRequestDTO empUpdateRequestDTO);
    void delete(Integer id);

    List<EmpResponseDTO> searchEmployee(String empName, String empAddress, String empEmail, String empPhone);
}
