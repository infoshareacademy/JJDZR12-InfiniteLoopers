package com.isa.webapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeForm {

    private String studentId;
    private Subject subject;
    private int grade;
    private String firstName;
    private String lastName;
}
