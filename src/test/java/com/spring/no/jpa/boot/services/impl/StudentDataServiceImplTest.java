package com.spring.no.jpa.boot.services.impl;

import com.spring.no.jpa.boot.entity.StudentData;
import com.spring.no.jpa.boot.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDataServiceImplTest {

    private StudentDataServiceImpl studentDataService;

    @BeforeEach
    void setUp() {
        studentDataService = new StudentDataServiceImpl();
    }

    @Test
    void testCreateStudent() {
        StudentData newStudent = new StudentData(9999, "Anand", "Delhi", 21, 5000.0);

        StudentData saved = studentDataService.createStudent(newStudent);

        assertNotNull(saved);
        assertEquals("Anand", saved.getStudentName());
        assertTrue(studentDataService.getAllStudentData().contains(newStudent));
    }

    @Test
    void testGetAllStudentData() {
        List<StudentData> students = studentDataService.getAllStudentData();

        assertNotNull(students);
        assertTrue(students.size() >= 5); // initial static data
    }

    @Test
    void testUpdateStudentData() {
        StudentData updateInfo = new StudentData(2314232, "Updated Name", "Updated Address", 25, 999.9);

        StudentData updated = studentDataService.updateStudentData(updateInfo, 2314232);

        assertEquals("Updated Name", updated.getStudentName());
        assertEquals("Updated Address", updated.getStudentAddress());
        assertEquals(25, updated.getStudentAge());
        assertEquals(999.9, updated.getStudentFees());
    }

    @Test
    void testUpdateStudentData_NotFound() {
        StudentData updateInfo = new StudentData(123456, "X", "Y", 20, 100.0);

        assertThrows(ResourceNotFoundException.class, () ->
                studentDataService.updateStudentData(updateInfo, 123456));
    }

    @Test
    void testGetStudentDataWithId() {
        StudentData student = studentDataService.getStudentDataWithId(3623123);

        assertNotNull(student);
        assertEquals("Monit Kumar", student.getStudentName());
    }

    @Test
    void testGetStudentDataWithId_NotFound() {
        assertThrows(ResourceNotFoundException.class, () ->
                studentDataService.getStudentDataWithId(1111111));
    }

    @Test
    void testDeleteByStudentId() {
        StudentData student = studentDataService.deleteByStudentId(1213422);

        assertNotNull(student);
        assertEquals(1213422, student.getStudentId());
    }

    @Test
    void testDeleteByStudentId_NotFound() {
        assertThrows(ResourceNotFoundException.class, () ->
                studentDataService.deleteByStudentId(888888));
    }

    @Test
    void testAddListOfStudent() {
        List<StudentData> newStudents = Arrays.asList(
                new StudentData(4444, "Student1", "Lucknow", 19, 2000.0),
                new StudentData(5555, "Student2", "Kanpur", 20, 2500.0)
        );

        List<StudentData> updatedList = studentDataService.addListOfStudent(newStudents);

        assertTrue(updatedList.containsAll(newStudents));
        assertTrue(updatedList.size() >= 7);
    }
}
