package com.example.pro.service;

import com.example.pro.dto.EduInfoResponse;
import com.example.pro.entity.EduInfo;
import com.example.pro.entity.School;
import com.example.pro.entity.Student;
import com.example.pro.repository.EduInfoRepository;
import com.example.pro.repository.SchoolRepository;
import com.example.pro.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EduInfoService {

    @Autowired
    private EduInfoRepository eduInfoRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SchoolRepository schoolRepository;

    public List<EduInfo> getAllEduInfo() {
        return eduInfoRepository.findAll();
    }

    public List<EduInfo> findByStudentId(Long studentId) {
        return eduInfoRepository.findByStudentId(studentId);
    }

    public List<EduInfoResponse> findEduInfoByStudentId(Long studentId, int page, int size, String sortBy, String order) {
        Sort sort = order.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Student student = studentRepository.findById(studentId).orElseThrow();

        List<EduInfo> eduInfos = eduInfoRepository.findByStudentId(studentId,pageable).stream().toList();
        List<EduInfoResponse> eduInfoResponses = new ArrayList<>();

        for (EduInfo eduInfo : eduInfos){
            EduInfoResponse eduInfoResponse = new EduInfoResponse();
            eduInfoResponse.setStatus(eduInfo.getStatus());
            eduInfoResponse.setStudentName(student.getName());
            eduInfoResponses.add(eduInfoResponse);
        }
        return eduInfoResponses;
    }

    public List<EduInfo> findBySchoolId(Long schoolId) {
        return eduInfoRepository.findBySchoolId(schoolId);
    }

    public EduInfo save(EduInfo eduInfo) {
        return eduInfoRepository.save(eduInfo);
    }

    public EduInfo findById(Long id) {
        return eduInfoRepository.findById(id).orElse(null);
    }
    public EduInfo changeEduStatus(Long id, String newStatus) {
        EduInfo eduInfo = eduInfoRepository.findById(id).orElseThrow();
        eduInfo.setStatus(newStatus);
        return eduInfoRepository.save(eduInfo);
    }
    public void exitIfStatusIsZero(Long studentId, Long schoolId) {
        List<EduInfo> eduInfoList = eduInfoRepository.findByStudentIdAndSchoolId(studentId, schoolId);
        for (EduInfo eduInfo : eduInfoList) {
            if (eduInfo.getStatus().equals("0")) {
                eduInfo.setStatus("3");
                eduInfoRepository.save(eduInfo);
                break;
            }
        }
    }

    public void delete(Long id) {
        eduInfoRepository.deleteById(id);
    }

    public void newEduInfo(Long studentId, Long schoolId) {
        List<EduInfo> eduInfoList = eduInfoRepository.findByStudentIdAndSchoolId(studentId, schoolId);
        if (eduInfoList.stream().anyMatch(eduInfo -> eduInfo.getStatus().equals("0"))) {
            throw new IllegalArgumentException("EduInfo with status '0' already exists for this student and school.");
        }

        Student student = studentRepository.findById(studentId).orElseThrow();
        School school = schoolRepository.findById(schoolId).orElseThrow();

        EduInfo newEduInfo = new EduInfo();
        newEduInfo.setStudent(student);
        newEduInfo.setSchool(school);
        newEduInfo.setStatus("0");

        eduInfoRepository.save(newEduInfo);
    }

    public List<EduInfo> findStudentSchool(Long studentId, Long schoolId) {
        return eduInfoRepository.findByStudentIdAndSchoolId(studentId, schoolId);
    }
}
