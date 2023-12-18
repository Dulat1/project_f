package com.example.pro.controller;

import com.example.pro.dto.StudentDTO;
import com.example.pro.dto.StudentResponse;
import com.example.pro.entity.Student;
import com.example.pro.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String order) {
        return studentService.findAll(page,size,sortBy,order);
    }

    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
    }

    @PostMapping
    public StudentResponse saveStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.save(studentDTO);
    }

    @PutMapping("/{id}/changeIin/{newIin}")
    public Student changeIin(@PathVariable Long id, @PathVariable String newIin) {
        return studentService.changeIin(id, newIin);
    }
}
