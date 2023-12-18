package com.example.pro.service;

import com.example.pro.entity.JobInfo;
import com.example.pro.repository.JobInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobInfoService {

    @Autowired
    private JobInfoRepository jobInfoRepository;

    public List<JobInfo> findByTeacherId(@Param("teacherId") Long teacherId) {
        return jobInfoRepository.findByTeacherId(teacherId);
    }

    public List<JobInfo> findBySchoolId(@Param("schoolId") Long schoolId) {
        return jobInfoRepository.findBySchoolId(schoolId);
    }

    public JobInfo changeJobInfoSchool(@Param("id") Long id, @Param("schoolId") Long schoolId) {
        JobInfo jobInfo = jobInfoRepository.findById(id).orElse(null);
        if (jobInfo != null) {
            jobInfo.setSchool(jobInfoRepository.getOne(schoolId).getSchool());
            return jobInfoRepository.save(jobInfo);
        }
        return null;
    }

    public JobInfo save(JobInfo jobInfo) {
        return jobInfoRepository.save(jobInfo);
    }

    public JobInfo findById(Long id) {
        return jobInfoRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        jobInfoRepository.deleteById(id);
    }
    // Другие методы, если необходимо
}
