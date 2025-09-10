package com.spring.no.jpa.boot.services;

import com.spring.no.jpa.boot.entity.StudentData;

import java.util.List;

public interface StudentDataService {
    public StudentData createStudent(StudentData studentData);

    public List<StudentData> getAllStudentData();


}
