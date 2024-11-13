package com.ra.jv240603_doanminhduc.service.department;

import com.ra.jv240603_doanminhduc.model.dto.department.DeptRequestDTO;
import com.ra.jv240603_doanminhduc.model.dto.department.DeptResponseDTO;
import com.ra.jv240603_doanminhduc.model.dto.department.DeptUpdateRequestDTO;

import java.util.List;

public interface DeptService {
    List<DeptResponseDTO> findAll();
    DeptResponseDTO create(DeptRequestDTO deptRequestDTO);
    DeptResponseDTO findById(Integer id);
    DeptResponseDTO update(DeptUpdateRequestDTO deptUpdateRequestDTO);
    String delete(Integer id);
}
