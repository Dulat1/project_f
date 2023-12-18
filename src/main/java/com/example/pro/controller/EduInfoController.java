package com.example.pro.controller;

import com.example.pro.dto.EduInfoResponse;
import com.example.pro.entity.EduInfo;
import com.example.pro.service.EduInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eduInfo")
public class EduInfoController {

    @Autowired
    private EduInfoService eduInfoService;

    @GetMapping("/findByStudentId/{studentId}")
    public List<EduInfoResponse> findByStudentIdWithPagination(
            @PathVariable Long studentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String order) {
        return eduInfoService.findEduInfoByStudentId(studentId, page, size, sortBy, order);
    }
    @GetMapping("/findBySchoolId/{schoolId}")
    public List<EduInfo> findBySchoolId(@PathVariable Long schoolId) {
        return eduInfoService.findBySchoolId(schoolId);
    }

    @GetMapping
    public List<EduInfo> getAllEduInfo() {
        return eduInfoService.getAllEduInfo();
    }

    @PostMapping("/newEduInfo")
    public ResponseEntity<?> newEduInfo(
            @RequestParam Long studentId,
            @RequestParam Long schoolId) {
        eduInfoService.newEduInfo(studentId, schoolId);
        return ResponseEntity.ok("Education Status added successfully!");
    }

    @PutMapping("/exit")
    public ResponseEntity<?> exitIfStatusIsZero(
            @RequestParam Long studentId,
            @RequestParam Long schoolId) {
        eduInfoService.exitIfStatusIsZero(studentId, schoolId);
        return ResponseEntity.ok("EduInfo exited if status was '0' for this student and school.");
    }

    @GetMapping("/findStudentSchool")
    public List<EduInfo> findStudentSchool(
            @RequestParam Long studentId,
            @RequestParam Long schoolId) {
        return eduInfoService.findStudentSchool(studentId, schoolId);
    }
}
