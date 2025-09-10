package com.spring.no.jpa.boot.controller;

import com.spring.no.jpa.boot.entity.StudentData;
import com.spring.no.jpa.boot.services.impl.StudentDataServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/no/jpa/student")
public class StudentController {

    @Autowired
    private StudentDataServiceImpl studentDataServiceImpl;

    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    @PostMapping("/save/studentData")
    public ResponseEntity<StudentData> addStudentData(@RequestBody StudentData studentData) {
        try {
            StudentData addStudentData = studentDataServiceImpl.createStudent(studentData);
            logger.info("student data have been added into DB !" + addStudentData);
            return ResponseEntity.ok(addStudentData);
        } catch (Exception e) {
            logger.info("something is wrong, Please check again !");
            throw e;
        }
    }

    @GetMapping("/studentDataList")
    public ResponseEntity<List<StudentData>> getAllStudentData() {
        List<StudentData> allStudentData = studentDataServiceImpl.getAllStudentData();
        logger.info("all student data is fetched from DB !" + allStudentData);
        return ResponseEntity.ok(allStudentData);
    }
}
