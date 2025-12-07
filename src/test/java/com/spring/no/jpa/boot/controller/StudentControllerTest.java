package com.spring.no.jpa.boot.controller;

import com.spring.no.jpa.boot.entity.StudentData;
import com.spring.no.jpa.boot.services.impl.StudentDataServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentControllerTest {

    @Mock
    private StudentDataServiceImpl studentDataServiceImpl;

    @InjectMocks
    private StudentController studentController;

    private StudentData student1;
    private StudentData student2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        student1 = new StudentData();
        student1.setStudentId(1);
        student1.setStudentName("Anand");

        student2 = new StudentData();
        student2.setStudentId(2);
        student2.setStudentName("Rahul");
    }

    @Test
    void testAddStudentData() {
        when(studentDataServiceImpl.createStudent(student1)).thenReturn(student1);

        ResponseEntity<StudentData> response = studentController.addStudentData(student1);

        assertEquals("Anand", response.getBody().getStudentName());
        verify(studentDataServiceImpl, times(1)).createStudent(student1);
    }

    @Test
    void testGetAllStudentData() {
        when(studentDataServiceImpl.getAllStudentData()).thenReturn(Arrays.asList(student1, student2));

        ResponseEntity<List<StudentData>> response = studentController.getAllStudentData();

        assertEquals(2, response.getBody().size());
        verify(studentDataServiceImpl, times(1)).getAllStudentData();
    }

    @Test
    void testUpdateStudentRecord() {
        when(studentDataServiceImpl.updateStudentData(student1, 1)).thenReturn(student1);

        ResponseEntity<StudentData> response = studentController.updateStudentRecord(student1, 1);

        assertEquals("Anand", response.getBody().getStudentName());
        verify(studentDataServiceImpl, times(1)).updateStudentData(student1, 1);
    }

    @Test
    void testGetSingleStudentWithId() {
        when(studentDataServiceImpl.getStudentDataWithId(1)).thenReturn(student1);

        ResponseEntity<StudentData> response = studentController.getSingleStudentWithId(1);

        assertEquals(1, response.getBody().getStudentId());
        verify(studentDataServiceImpl, times(1)).getStudentDataWithId(1);
    }

    @Test
    void testDeleteStudentById() {
        when(studentDataServiceImpl.deleteByStudentId(1)).thenReturn(student1);

        ResponseEntity<StudentData> response = studentController.deleteStudentById(1);

        assertEquals("Anand", response.getBody().getStudentName());
        verify(studentDataServiceImpl, times(1)).deleteByStudentId(1);
    }

    @Test
    void testAddListOfStudent() {
        List<StudentData> students = Arrays.asList(student1, student2);
        when(studentDataServiceImpl.addListOfStudent(students)).thenReturn(students);

        ResponseEntity<List<StudentData>> response = studentController.addListOfStudent(students);

        assertEquals(2, response.getBody().size());
        verify(studentDataServiceImpl, times(1)).addListOfStudent(students);
    }
}
