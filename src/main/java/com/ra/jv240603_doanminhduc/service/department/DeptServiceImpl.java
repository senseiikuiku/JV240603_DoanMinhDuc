package com.ra.jv240603_doanminhduc.service.department;

import com.ra.jv240603_doanminhduc.model.dto.department.DeptRequestDTO;
import com.ra.jv240603_doanminhduc.model.dto.department.DeptResponseDTO;
import com.ra.jv240603_doanminhduc.model.dto.department.DeptUpdateRequestDTO;
import com.ra.jv240603_doanminhduc.model.entity.Department;
import com.ra.jv240603_doanminhduc.repository.DeptRepository;
import com.ra.jv240603_doanminhduc.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService{

    private final DeptRepository deptRepository;
    private final EmpRepository empRepository;
    @Autowired
    public DeptServiceImpl(DeptRepository deptRepository, EmpRepository empRepository) {
        this.deptRepository = deptRepository;
        this.empRepository = empRepository;
    }

    @Override
    public List<DeptResponseDTO> findAll() {
        List<Department> depts = deptRepository.findAll();
        List<DeptResponseDTO> deptResponseDTOs = new ArrayList<>();
        for (Department dept : depts) {
            DeptResponseDTO deptResponseDTO = new DeptResponseDTO();
            deptResponseDTO.setId(dept.getId());
            deptResponseDTO.setDeptName(dept.getDeptName());
            deptResponseDTO.setDeptDescription(dept.getDeptDescription());
            deptResponseDTO.setDeptStatus(dept.isDeptStatus()?"Hoạt động":"Không hoạt động");
            deptResponseDTOs.add(deptResponseDTO);
        }
        return deptResponseDTOs;
    }

    @Override
    public DeptResponseDTO create(DeptRequestDTO deptRequestDTO) {
        if (deptRepository.findByDeptName(deptRequestDTO.getDeptName()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Department name already exists!"
            );
        }
        Department department = Department.builder()
                .deptName(deptRequestDTO.getDeptName())
                .deptDescription(deptRequestDTO.getDeptDescription())
                .deptStatus(deptRequestDTO.isDeptStatus())
                .build();
        Department newDept = deptRepository.save(department);

        return DeptResponseDTO.builder()
                .id(newDept.getId())
                .deptName(newDept.getDeptName())
                .deptDescription(newDept.getDeptDescription())
                .deptStatus(newDept.isDeptStatus()?"Hoạt động":"Không hoạt động")
                .build();
    }

    @Override
    public DeptResponseDTO findById(Integer id) {
        Department department = deptRepository.findById(id).orElse(null);

        return DeptResponseDTO.builder()
                .id(department.getId())
                .deptName(department.getDeptName())
                .deptDescription(department.getDeptDescription())
                .deptStatus(department.isDeptStatus()?"Hoạt động":"Không hoạt động")
                .build();
    }

    @Override
    public DeptResponseDTO update(DeptUpdateRequestDTO deptUpdateRequestDTO) {
        if (deptRepository.findByDeptNameIgnoreCase(deptUpdateRequestDTO.getDeptName()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Department name already exists!"
            );
        }
        Department department = Department.builder()
                .id(deptUpdateRequestDTO.getId())
                .deptName(deptUpdateRequestDTO.getDeptName())
                .deptDescription(deptUpdateRequestDTO.getDeptDescription())
                .deptStatus(deptUpdateRequestDTO.isDeptStatus())
                .build();
        Department updatedDept = deptRepository.save(department);

        return DeptResponseDTO.builder()
                .id(updatedDept.getId())
                .deptName(updatedDept.getDeptName())
                .deptDescription(updatedDept.getDeptDescription())
                .deptStatus(updatedDept.isDeptStatus()?"Hoạt động":"Không hoạt động")
                .build();
    }

    @Override
    public String delete(Integer id) {
        boolean hasEmployees = empRepository.existsByDepartment_Id(id);

        if (hasEmployees) {
            throw new IllegalArgumentException("Department contains employees and cannot be deleted.");
        } else {
            deptRepository.deleteById(id);
            return "Department deleted successfully.";
        }
    }
}
