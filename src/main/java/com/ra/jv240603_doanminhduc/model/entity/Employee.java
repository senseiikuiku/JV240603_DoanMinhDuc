package com.ra.jv240603_doanminhduc.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Stack;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Emp_name",length = 100,nullable = false)
    private String empName;
    @Column(name = "Emp_BirthOfDate",nullable = false)
    private Date birthOfDate;
    @Column(name = "Emp_sex",nullable = false)
    private boolean empSex;
    @Column(name = "Emp_address",nullable = false)
    private String empAddress;
    @Column(name = "Emp_email",length = 200,nullable = false,unique = true)
    private String empEmail;
    @Column(name = "Emp_phone",length = 11,nullable = false,unique = true)
    private String empPhone;
    @Column(name = "Emp_avatar")
    private String empAvatar;
    @Column(name = "Emp_status")
    private boolean empStatus;
    @ManyToOne
    @JoinColumn(name = "Dept_id",referencedColumnName = "id")
    private Department department;

}
