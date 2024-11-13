package com.ra.jv240603_doanminhduc.validate;

import com.ra.jv240603_doanminhduc.repository.EmpRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmpValidate implements ConstraintValidator<EmpUnique,String> {
    @Autowired
    private EmpRepository empRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !empRepository.existsEmployeeByEmpEmail(value) && !empRepository.existsEmployeeByEmpPhone(value);
    }
}
