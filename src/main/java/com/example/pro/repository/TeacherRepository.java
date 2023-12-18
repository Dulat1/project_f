package com.example.pro.repository;

import com.example.pro.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // Дополнительные методы могут быть добавлены по мере необходимости
}
