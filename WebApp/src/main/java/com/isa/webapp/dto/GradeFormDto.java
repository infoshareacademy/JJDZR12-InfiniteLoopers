package com.isa.webapp.dto;

import com.isa.webapp.model.Subject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeFormDto {

    private String studentId;
    private Subject subject;
    private int grade;
    private String firstName;
    private String lastName;
}
