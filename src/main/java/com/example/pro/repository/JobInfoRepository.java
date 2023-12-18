package com.example.pro.repository;

import com.example.pro.entity.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobInfoRepository extends JpaRepository<JobInfo, Long> {
    List<JobInfo> findByTeacherId(Long teacherId);
    List<JobInfo> findBySchoolId(Long schoolId); // Добавленный метод
}
