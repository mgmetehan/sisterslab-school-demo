package com.sisterslab.sisterslabschooldemo.service;

import com.sisterslab.sisterslabschooldemo.converter.SchoolConverter;
import com.sisterslab.sisterslabschooldemo.dto.request.SchoolRequest;
import com.sisterslab.sisterslabschooldemo.dto.response.SchoolCreateResponse;
import com.sisterslab.sisterslabschooldemo.exception.SchoolAlreadyExistsException;
import com.sisterslab.sisterslabschooldemo.model.School;
import com.sisterslab.sisterslabschooldemo.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public SchoolCreateResponse createSchool(SchoolRequest request) {
        Optional<School> schoolByName = schoolRepository.findBySchoolName(request.getSchoolName());
        if (schoolByName.isPresent()) {
            throw new SchoolAlreadyExistsException("School name already exists with name: " +
                    request.getSchoolName());
        }
        return SchoolConverter.convertToSchoolCreateResponse(
                schoolRepository.save(SchoolConverter.convertToSchool(request)));
    }

    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }
}
