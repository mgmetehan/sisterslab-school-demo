package com.sisterslab.sisterslabschooldemo.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SchoolResponse {
    private String schoolName;
    private Date createdDate;
}
