package com.example.pro.service;

import com.example.pro.entity.Teacher;
import com.example.pro.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Page<Teacher> findAll(int page, int size, String sortBy, String order) {
        return teacherRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sortBy)));
    }

    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // Другие методы, если необходимо
}
