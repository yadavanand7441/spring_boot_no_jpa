package com.spring.no.jpa.boot.services;

import com.spring.no.jpa.boot.entity.StudentData;

import java.util.List;

public interface StudentDataService {
    public StudentData createStudent(StudentData studentData);

    public List<StudentData> getAllStudentData();
    public StudentData updateStudentData(StudentData studentData,int studentId);
    public StudentData getStudentDataWithId(int studentId);
    public StudentData deleteByStudentId(int studentId);
    public List<StudentData> addListOfStudent(List<StudentData>listOfStudentData);


}
