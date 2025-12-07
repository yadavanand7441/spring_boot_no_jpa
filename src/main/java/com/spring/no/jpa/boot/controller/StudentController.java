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

    private StudentDataServiceImpl studentDataServiceImpl;
    @Autowired
    public StudentController(StudentDataServiceImpl studentDataServiceImpl) {
        this.studentDataServiceImpl = studentDataServiceImpl;
    }

    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    @PostMapping("/save/studentData")
    public ResponseEntity<StudentData> addStudentData(@RequestBody StudentData studentData) {
        try {
            StudentData addStudentData = studentDataServiceImpl.createStudent(studentData);
            logger.info("student data have been added into DB{} ", addStudentData);
            return ResponseEntity.ok(addStudentData);
        } catch (Exception e) {
            logger.info("something is wrong, Please check again !");
            throw e;
        }
    }

    @GetMapping("/studentDataList")
    public ResponseEntity<List<StudentData>> getAllStudentData() {
        List<StudentData> allStudentData = studentDataServiceImpl.getAllStudentData();
        logger.info("all student data is fetched from DB{} " , allStudentData);
        return ResponseEntity.ok(allStudentData);
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<StudentData> updateStudentRecord(@RequestBody StudentData studentData, @PathVariable("studentId") int studentId) {
        StudentData updatedStudentData = studentDataServiceImpl.updateStudentData(studentData, studentId);
        logger.info("student data updated{} " , updatedStudentData);
        return ResponseEntity.ok(updatedStudentData);
    }

    @GetMapping("/fetch/{studentId}")
    public ResponseEntity<StudentData> getSingleStudentWithId(@PathVariable("studentId") int studentId) {
        StudentData studentDataWithId = studentDataServiceImpl.getStudentDataWithId(studentId);
        logger.info("student is found with given id{} " , studentDataWithId);
        return ResponseEntity.ok(studentDataWithId);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<StudentData> deleteStudentById(@PathVariable("studentId") int studentId) {
        StudentData deleteByStudentId = studentDataServiceImpl.deleteByStudentId(studentId);
        logger.info("student data have been deleted with given id{} " , studentId);
        return ResponseEntity.ok(deleteByStudentId);
    }

    @PostMapping("/listOfStudent")
    public ResponseEntity<List<StudentData>> addListOfStudent(@RequestBody List<StudentData> listOfStudent) {
        List<StudentData> addListOfStudent = studentDataServiceImpl.addListOfStudent(listOfStudent);
        logger.info("list of student have been added into DB{} " , addListOfStudent);
        return ResponseEntity.ok(addListOfStudent);
    }
}
