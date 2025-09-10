package com.spring.no.jpa.boot.services.impl;

import com.spring.no.jpa.boot.entity.StudentData;
import com.spring.no.jpa.boot.exceptions.ResourceNotFoundException;
import com.spring.no.jpa.boot.services.StudentDataService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentDataServiceImpl implements StudentDataService {

    //dummy database
    private static List<StudentData> studentDataList = new ArrayList<>();

    static {
        studentDataList.add(new StudentData(2314232, "Rahul Sharma", "Patna", 9, 550.0));
        studentDataList.add(new StudentData(3623123, "Monit Kumar", "Gaya", 11, 430.0));
        studentDataList.add(new StudentData(1213422, "Tony Sharma", "Patna", 9, 550.0));
        studentDataList.add(new StudentData(2314232, "Mohan Verma", "Muzaffarpur", 7, 300.0));
        studentDataList.add(new StudentData(2314232, "Sonu Sharma", "Patna", 9, 700.9));
    }


    //adding single student
    @Override
    public StudentData createStudent(StudentData studentData) {
        studentDataList.add(studentData);
        return studentData;
    }

    //get all studentData
    @Override
    public List<StudentData> getAllStudentData() {
        return studentDataList;
    }

    @Override
    public StudentData updateStudentData(StudentData studentData, int studentId) {
        StudentData updateStudentData = studentDataList.stream().filter(e -> e.getStudentId() == studentId).findFirst().orElseThrow(() ->
                new ResourceNotFoundException("you can not update the student data because student is not present with given id" + studentId));
        updateStudentData.setStudentName(studentData.getStudentName());
        updateStudentData.setStudentAddress(studentData.getStudentAddress());
        updateStudentData.setStudentAge(studentData.getStudentAge());
        updateStudentData.setStudentFees(studentData.getStudentFees());
        return updateStudentData;
    }

    @Override
    public StudentData getStudentDataWithId(int studentId) {
        StudentData studentDataWithId = studentDataList.stream().filter(e -> e.getStudentId() == studentId).findFirst().orElseThrow(() ->
                new ResourceNotFoundException("student data is not found with given id please check !" + studentId));
        return studentDataWithId;
    }

    @Override
    public StudentData deleteByStudentId(int studentId) {
        StudentData deleteStudentDataById = studentDataList.stream().filter(e -> e.getStudentId() == studentId).findFirst().orElseThrow(() ->
                new ResourceNotFoundException("student data is not deleted because that student is not present with given id" + studentId));
        return deleteStudentDataById;
    }

    @Override
    public List<StudentData> addListOfStudent(List<StudentData> listOfStudentData) {
        studentDataList.addAll(listOfStudentData);
        return studentDataList;
    }


}
