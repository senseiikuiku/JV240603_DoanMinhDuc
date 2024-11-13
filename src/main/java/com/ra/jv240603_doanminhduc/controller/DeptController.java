package com.ra.jv240603_doanminhduc.controller;

import com.ra.jv240603_doanminhduc.model.dto.department.DeptRequestDTO;
import com.ra.jv240603_doanminhduc.model.dto.department.DeptResponseDTO;
import com.ra.jv240603_doanminhduc.model.dto.department.DeptUpdateRequestDTO;
import com.ra.jv240603_doanminhduc.model.entity.Department;
import com.ra.jv240603_doanminhduc.service.department.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
public class DeptController {

    private final DeptService deptService;
    @Autowired
    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    @GetMapping
    public ResponseEntity<List<DeptResponseDTO>> index() {
        List<DeptResponseDTO> depts = deptService.findAll();
        return new ResponseEntity<>(depts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DeptRequestDTO deptRequestDTO) {
        try {
            DeptResponseDTO deptResponseDTO = deptService.create(deptRequestDTO);
            return new ResponseEntity<>(deptResponseDTO, HttpStatus.CREATED);
        } catch (ResponseStatusException ex) {

            return new ResponseEntity<>("Tên đã tồn tại", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeptResponseDTO> findById(@PathVariable Integer id) {
        DeptResponseDTO deptResponseDTO = deptService.findById(id);
        return new ResponseEntity<>(deptResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                                  @RequestBody DeptUpdateRequestDTO deptUpdateRequestDTO) {
        try {
            deptUpdateRequestDTO.setId(id);
            DeptResponseDTO deptResponseDTO = deptService.update(deptUpdateRequestDTO);
            return new ResponseEntity<>(deptResponseDTO, HttpStatus.CREATED);
        }catch (ResponseStatusException ex) {
            return new ResponseEntity<>("Tên phòng ban không được trùng", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            String message = deptService.delete(id);
            return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
