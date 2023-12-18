package com.example.pro.service;

import com.example.pro.dto.StudentDTO;
import com.example.pro.dto.StudentResponse;
import com.example.pro.entity.Student;
import com.example.pro.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll(int page, int size, String sortBy, String order) {
        Sort sort = order.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page,size,sort);

        Page<Student> students = studentRepository.findAll(pageable);
        return students.getContent();
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public StudentResponse save(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setSurname(studentDTO.getSurname());
        student.setIin(studentDTO.getIin());
        studentRepository.save(student);
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setSurname(student.getSurname());
        studentResponse.setName(student.getName());
        studentResponse.setIin(student.getIin());
        return studentResponse;
    }

    public Student changeIin(Long id, String newIin) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setIin(newIin);
            return studentRepository.save(student);
        }
        throw new IllegalArgumentException("Student not found with id: " + id);
    }
}
