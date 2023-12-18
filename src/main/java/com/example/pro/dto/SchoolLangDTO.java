package com.example.pro.dto;

import com.example.pro.entity.SchoolTranslation;
import lombok.Data;

import java.util.List;

@Data
public class SchoolLangDTO {
    private SchoolDTO schoolDTO;
    private List<SchoolTranslation> schoolTranslations;
}
