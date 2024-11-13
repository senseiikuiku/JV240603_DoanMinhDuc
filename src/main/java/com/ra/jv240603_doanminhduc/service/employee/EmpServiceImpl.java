package com.ra.jv240603_doanminhduc.service.employee;

import com.ra.jv240603_doanminhduc.model.dto.employee.EmpRequestDTO;
import com.ra.jv240603_doanminhduc.model.dto.employee.EmpResponseDTO;
import com.ra.jv240603_doanminhduc.model.dto.employee.EmpUpdateRequestDTO;
import com.ra.jv240603_doanminhduc.model.entity.Employee;
import com.ra.jv240603_doanminhduc.repository.DeptRepository;
import com.ra.jv240603_doanminhduc.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmpServiceImpl implements EmpService {

    private final EmpRepository empRepository;
    private final DeptRepository deptRepository;

    public EmpServiceImpl(EmpRepository empRepository, DeptRepository deptRepository) {
        this.empRepository = empRepository;
        this.deptRepository = deptRepository;
    }

    @Autowired

    @Override
    public List<EmpResponseDTO> findAll() {
        List<Employee> employees = empRepository.findAll();
        List<EmpResponseDTO> empResponseDTOs = new ArrayList<>();
        for (Employee employee : employees) {
            EmpResponseDTO empResponseDTO = new EmpResponseDTO();
            empResponseDTO.setId(employee.getId());
            empResponseDTO.setEmpName(employee.getEmpName());
            empResponseDTO.setBirthOfDate(employee.getBirthOfDate());
            empResponseDTO.setEmpAddress(employee.getEmpAddress());
            empResponseDTO.setEmpSex(employee.isEmpSex()?"Nam":"Nữ");
            empResponseDTO.setEmpPhone(employee.getEmpPhone());
            empResponseDTO.setEmpEmail(employee.getEmpEmail());
            empResponseDTO.setEmpAvatar(employee.getEmpAvatar());
            empResponseDTO.setEmpStatus(employee.isEmpStatus()?"Đang làm việc":"Nghỉ việc");
            empResponseDTO.setDepartment(employee.getDepartment());
            empResponseDTOs.add(empResponseDTO);
        }
        return empResponseDTOs;
    }

    @Override
    public EmpResponseDTO create(EmpRequestDTO empRequestDTO) {
        Employee employee = Employee.builder()
                .empName(empRequestDTO.getEmpName())
                .birthOfDate(empRequestDTO.getBirthOfDate())
                .empAddress(empRequestDTO.getEmpAddress())
                .empSex(empRequestDTO.isEmpSex())
                .empPhone(empRequestDTO.getEmpPhone())
                .empEmail(empRequestDTO.getEmpEmail())
                .empAvatar(empRequestDTO.getEmpAvatar())
                .empStatus(empRequestDTO.isEmpStatus())
                .department(deptRepository.findById(empRequestDTO.getDepartment()).orElseThrow(()-> new NoSuchElementException("Department not found")))
                .build();
        Employee newEmployee = empRepository.save(employee);

        return EmpResponseDTO.builder()
                .id(newEmployee.getId())
                .empName(newEmployee.getEmpName())
                .birthOfDate(newEmployee.getBirthOfDate())
                .empAddress(newEmployee.getEmpAddress())
                .empSex(newEmployee.isEmpSex()?"Nam":"Nữ")
                .empPhone(newEmployee.getEmpPhone())
                .empEmail(newEmployee.getEmpEmail())
                .empAvatar(newEmployee.getEmpAvatar())
                .empStatus(newEmployee.isEmpStatus()?"Đang làm việc":"Nghỉ việc")
                .department(newEmployee.getDepartment())
                .build();
    }

    @Override
    public EmpResponseDTO findById(Integer id) {
        Employee employee = empRepository.findById(id).orElse(null);

        return EmpResponseDTO.builder()
                .id(employee.getId())
                .empName(employee.getEmpName())
                .birthOfDate(employee.getBirthOfDate())
                .empAddress(employee.getEmpAddress())
                .empSex(employee.isEmpSex()?"Nam":"Nữ")
                .empPhone(employee.getEmpPhone())
                .empEmail(employee.getEmpEmail())
                .empAvatar(employee.getEmpAvatar())
                .empStatus(employee.isEmpStatus()?"Đang làm việc":"Nghỉ việc")
                .department(employee.getDepartment())
                .build();
    }

    @Override
    public EmpResponseDTO update(EmpUpdateRequestDTO empUpdateRequestDTO) {
        Employee employee = Employee.builder()
                .id(empUpdateRequestDTO.getId())
                .empName(empUpdateRequestDTO.getEmpName())
                .birthOfDate(empUpdateRequestDTO.getBirthOfDate())
                .empAddress(empUpdateRequestDTO.getEmpAddress())
                .empSex(empUpdateRequestDTO.isEmpSex())
                .empPhone(empUpdateRequestDTO.getEmpPhone())
                .empAvatar(empUpdateRequestDTO.getEmpAvatar())
                .empEmail(empUpdateRequestDTO.getEmpEmail())
                .empStatus(empUpdateRequestDTO.isEmpStatus())
                .department(deptRepository.findById(empUpdateRequestDTO.getDepartment()).orElseThrow(()->new RuntimeException("Department not found")))
                .build();
        Employee updateEmployee = empRepository.save(employee);

        return EmpResponseDTO.builder()
                .id(updateEmployee.getId())
                .empName(updateEmployee.getEmpName())
                .birthOfDate(updateEmployee.getBirthOfDate())
                .empAddress(updateEmployee.getEmpAddress())
                .empSex(updateEmployee.isEmpSex()?"Nam":"Nữ")
                .empPhone(updateEmployee.getEmpPhone())
                .empEmail(updateEmployee.getEmpEmail())
                .empAvatar(updateEmployee.getEmpAvatar())
                .empStatus(updateEmployee.isEmpStatus()?"Đang làm việc":"Nghỉ việc")
                .department(updateEmployee.getDepartment())
                .build();
    }

    @Override
    public void delete(Integer id) {
        Employee employee = empRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));


        employee.setEmpStatus(false);

        empRepository.save(employee);
    }

    @Override
    public List<EmpResponseDTO> searchEmployee(String empName, String empAddress, String empEmail, String empPhone) {
        List<Employee> employees = empRepository.findByEmpNameContainingIgnoreCaseOrEmpAddressContainingIgnoreCaseOrEmpEmailContainingIgnoreCaseOrEmpPhoneContainingIgnoreCase(
                empName, empAddress, empEmail, empPhone
        );

        return employees.stream().map(employee -> {
            EmpResponseDTO empResponseDTO = new EmpResponseDTO();
            empResponseDTO.setId(employee.getId());
            empResponseDTO.setEmpName(employee.getEmpName());
            empResponseDTO.setBirthOfDate(employee.getBirthOfDate());
            empResponseDTO.setEmpSex(employee.isEmpSex()?"Nam":"Nữ");
            empResponseDTO.setEmpAddress(employee.getEmpAddress());
            empResponseDTO.setEmpEmail(employee.getEmpEmail());
            empResponseDTO.setEmpPhone(employee.getEmpPhone());
            empResponseDTO.setEmpAvatar(employee.getEmpAvatar());
            empResponseDTO.setDepartment(employee.getDepartment());
            empResponseDTO.setEmpStatus(employee.isEmpStatus()?"Đang làm việc":"Nghỉ việc");
            return empResponseDTO;
        }).toList();
    }

}
