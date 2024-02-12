package com.sisterslab.sisterslabschooldemo.service;

import com.sisterslab.sisterslabschooldemo.converter.SchoolConverter;
import com.sisterslab.sisterslabschooldemo.dto.request.SchoolRequest;
import com.sisterslab.sisterslabschooldemo.dto.response.SchoolResponse;
import com.sisterslab.sisterslabschooldemo.exception.SchoolAlreadyExistsException;
import com.sisterslab.sisterslabschooldemo.exception.SchoolNotFoundException;
import com.sisterslab.sisterslabschooldemo.model.School;
import com.sisterslab.sisterslabschooldemo.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public SchoolResponse createSchool(SchoolRequest request) {
        Optional<School> schoolByName = schoolRepository.findBySchoolName(request.getSchoolName());
        if (schoolByName.isPresent()) {
            throw new SchoolAlreadyExistsException("School name already exists with name: " +
                    request.getSchoolName());
        }
        return SchoolConverter.convertToSchoolResponse(
                schoolRepository.save(SchoolConverter.convertToSchool(request)));
    }

    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }

    public SchoolResponse getSchoolById(Long id) {
        return SchoolConverter.convertToSchoolResponse(findById(id));
    }

    private School findById(Long id) {
        return schoolRepository.findById(id)
                .orElseThrow(() -> new SchoolNotFoundException("School not found !! " + id));
    }

    public void updateSchool(Long id, SchoolRequest request) {
        School oldSchool = findById(id);
        oldSchool.setSchoolName(request.getSchoolName());
        schoolRepository.save(oldSchool);
    }

    public List<School> getSchool(String name) {
        if (name == null) {
            return schoolRepository.findAll();
        } else {
            return schoolRepository.findAllBySchoolName(name);
        }
    }
}





