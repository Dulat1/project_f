package com.example.pro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "school_translation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @Column(name = "language_code")
    private String languageCode;

    @Column(name = "translated_name")
    private String translatedName;

    @Column(name = "translated_address")
    private String translatedAddress;

    @Column(name = "translated_level")
    private String translatedLevel;

}
