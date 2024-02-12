package com.sisterslab.sisterslabschooldemo.controller;

import com.sisterslab.sisterslabschooldemo.dto.request.SchoolRequest;
import com.sisterslab.sisterslabschooldemo.dto.response.SchoolCreateResponse;
import com.sisterslab.sisterslabschooldemo.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    @PostMapping
    public SchoolCreateResponse createSchool(@RequestBody SchoolRequest request) {
        return schoolService.createSchool(request);
    }

    @DeleteMapping("/{id}")
    public void deleteSchool(@PathVariable Long id){
        schoolService.deleteSchool(id);
    }
}
