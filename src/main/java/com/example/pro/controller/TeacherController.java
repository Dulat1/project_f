package com.example.pro.controller;

import com.example.pro.entity.Teacher;
import com.example.pro.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public Page<Teacher> getAllTeachers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String order) {
        return teacherService.findAll(page, size, sortBy, order);
    }

    @GetMapping("/{id}")
    public Optional<Teacher> getTeacherById(@PathVariable Long id) {
        return teacherService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.delete(id);
    }

    @PostMapping
    public Teacher saveTeacher(@RequestBody Teacher teacher) {
        return teacherService.save(teacher);
    }
}
