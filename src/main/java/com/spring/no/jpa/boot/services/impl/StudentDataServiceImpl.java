package com.spring.no.jpa.boot.services.impl;

import com.spring.no.jpa.boot.entity.StudentData;
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

}
