package com.example.pro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private School school;

    private String jobTitle;
    private int salary;
    // Другие поля по вашему усмотрению
}
