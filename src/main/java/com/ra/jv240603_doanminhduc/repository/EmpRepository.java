package com.ra.jv240603_doanminhduc.repository;

import com.ra.jv240603_doanminhduc.model.entity.Employee;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpRepository extends JpaRepository<Employee, Integer> {
    boolean existsByDepartment_Id(Integer id);

    List<Employee> findByEmpNameContainingIgnoreCaseOrEmpAddressContainingIgnoreCaseOrEmpEmailContainingIgnoreCaseOrEmpPhoneContainingIgnoreCase(
            String empName, String empAddress, String empEmail, String empPhone
    );

    Boolean existsEmployeeByEmpEmail(String email);
    Boolean existsEmployeeByEmpPhone(String phone);
}
