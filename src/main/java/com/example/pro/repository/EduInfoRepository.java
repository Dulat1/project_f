package com.example.pro.repository;

import com.example.pro.entity.EduInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EduInfoRepository extends JpaRepository<EduInfo, Long> {
    List<EduInfo> findByStudentId(Long studentId);
    Page<EduInfo> findByStudentId(Long studentId, Pageable pageable);
    List<EduInfo> findBySchoolId(Long schoolId);
    List<EduInfo> findByStudentIdAndSchoolId(Long studentId, Long schoolId);

}
