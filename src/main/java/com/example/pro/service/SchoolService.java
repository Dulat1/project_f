package com.example.pro.service;

import com.example.pro.dto.SchoolDTO;
import com.example.pro.entity.School;
import com.example.pro.entity.SchoolTranslation;
import com.example.pro.repository.SchoolRepository;
import com.example.pro.repository.SchoolTranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private SchoolTranslationRepository schoolTranslationRepository;

    public List<SchoolDTO> findAll(int page, int size, String sortBy, String order, String lang) {
        Sort sort = order.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<School> schools = schoolRepository.findAll(pageable);

        return schools.stream().map(school -> convertToDTO(school,lang))
                .collect(Collectors.toList());
    }

    private SchoolDTO convertToDTO(School school, String lang) {
        SchoolDTO schoolDTO = new SchoolDTO();
        schoolDTO.setId(school.getId());

        if (school.getSchoolTranslations() != null && !school.getSchoolTranslations().isEmpty()) {
            SchoolTranslation schoolTranslation = school.getSchoolTranslations().stream()
                    .filter(translation -> lang.equals(translation.getLanguageCode()))
                    .findFirst()
                    .orElse(null);

            if (schoolTranslation != null) {
                schoolDTO.setTranslatedName(schoolTranslation.getTranslatedName());
                schoolDTO.setTranslatedAddress(schoolTranslation.getTranslatedAddress());
                schoolDTO.setTranslatedLevel(schoolTranslation.getTranslatedLevel());
            }
        }
        return schoolDTO;
    }

    public Optional<SchoolDTO> findById(Long id, String languageCode) {
        Optional<School> byId = schoolRepository.findById(id);

        return byId.map(school -> {
            if (school.getSchoolTranslations() != null && !school.getSchoolTranslations().isEmpty()) {
                SchoolTranslation selectedTranslation = school.getSchoolTranslations().stream()
                        .filter(translation -> languageCode.equals(translation.getLanguageCode()))
                        .findFirst()
                        .orElse(null);

                if (selectedTranslation != null) {
                    SchoolDTO productDTO = new SchoolDTO();
                    productDTO.setId(school.getId());
                    productDTO.setTranslatedName(selectedTranslation.getTranslatedName());
                    productDTO.setTranslatedAddress(selectedTranslation.getTranslatedAddress());
                    productDTO.setTranslatedLevel(selectedTranslation.getTranslatedLevel());
                    return productDTO;
                }
            }
            return null;
        });
    }

    public void delete(Long id) {
        schoolRepository.deleteById(id);
    }

    public void save(List<SchoolTranslation> schoolTranslations) {
        School school = new School();
        school.setSchoolTranslations(schoolTranslations);
        schoolRepository.save(school);
        for (SchoolTranslation translation : schoolTranslations) {
            translation.setSchool(school);
            schoolTranslationRepository.save(translation);
        }
    }
}
