package com.example.pro.controller;

import com.example.pro.dto.SchoolLangDTO;
import com.example.pro.entity.School;
import com.example.pro.dto.SchoolDTO;
import com.example.pro.entity.SchoolTranslation;
import com.example.pro.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping
    public List<SchoolDTO> getAllSchools(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String order,
            @RequestHeader("language") String lang) {
        return schoolService.findAll(page, size, sortBy, order,lang);
    }

    @GetMapping("/{id}")
    public Optional<SchoolDTO> getSchoolById(@PathVariable Long id, @RequestHeader("language") String lang) {
        return schoolService.findById(id,lang);
    }

    @DeleteMapping("/{id}")
    public void deleteSchool(@PathVariable Long id) {
        schoolService.delete(id);
    }

    @PostMapping
    public ResponseEntity<?> saveSchool(@RequestBody List<SchoolTranslation> schoolTranslations) {
        schoolService.save(schoolTranslations);
        return ResponseEntity.ok("School saved successfully!");
    }
}
