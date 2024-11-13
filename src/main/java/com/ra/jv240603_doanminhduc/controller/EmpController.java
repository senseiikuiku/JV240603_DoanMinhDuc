package com.ra.jv240603_doanminhduc.controller;

import com.ra.jv240603_doanminhduc.model.dto.employee.EmpRequestDTO;
import com.ra.jv240603_doanminhduc.model.dto.employee.EmpResponseDTO;
import com.ra.jv240603_doanminhduc.model.dto.employee.EmpUpdateRequestDTO;
import com.ra.jv240603_doanminhduc.repository.EmpRepository;
import com.ra.jv240603_doanminhduc.service.employee.EmpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmpController {
    private final EmpService empService;
    @Autowired
    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping
    public ResponseEntity<List<EmpResponseDTO>> index() {
        List<EmpResponseDTO> empResponseDTOS = empService.findAll();
        return new ResponseEntity<>(empResponseDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmpResponseDTO> create(@Valid @RequestBody EmpRequestDTO empRequestDTO) {
        EmpResponseDTO empResponseDTO = empService.create(empRequestDTO);
        return new ResponseEntity<>(empResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpResponseDTO> findById(@PathVariable Integer id) {
        EmpResponseDTO empResponseDTO = empService.findById(id);
        return new ResponseEntity<>(empResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpResponseDTO> update(@PathVariable Integer id,@Valid
    @RequestBody EmpUpdateRequestDTO empUpdateRequestDTO) {
        empUpdateRequestDTO.setId(id);
        EmpResponseDTO empResponseDTO = empService.update(empUpdateRequestDTO);
        return new ResponseEntity<>(empResponseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmpResponseDTO> delete(@PathVariable Integer id) {
        empService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmpResponseDTO>> search(
            @RequestParam(name = "empName", required = false) String empName,
            @RequestParam(name = "empAddress", required = false) String empAddress,
            @RequestParam(name = "empEmail", required = false) String empEmail,
            @RequestParam(name = "empPhone", required = false) String empPhone) {

        List<EmpResponseDTO> responseDTOS = empService.searchEmployee(empName, empAddress, empEmail, empPhone);
        return new ResponseEntity<>(responseDTOS, HttpStatus.OK);
    }

}
