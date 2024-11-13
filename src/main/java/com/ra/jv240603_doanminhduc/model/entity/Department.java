package com.ra.jv240603_doanminhduc.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Dept_name",length = 100,nullable = false,unique = true)
    private String deptName;
    @Column(name = "Dept_description")
    private String deptDescription;
    @Column(name = "Dept_status")
    private boolean deptStatus;
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<Employee> employees;
}