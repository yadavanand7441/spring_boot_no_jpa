package com.spring.no.jpa.boot.services;

import com.spring.no.jpa.boot.entity.StudentData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentDataServiceTest {

    @Mock
    private StudentDataService studentDataService;

    private StudentData student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        student = new StudentData(1, "Anand", "Delhi", 22, 15000.50);
    }

    @Test
    void testCreateStudent() {
        when(studentDataService.createStudent(any(StudentData.class))).thenReturn(student);

        StudentData saved = studentDataService.createStudent(student);

        assertEquals(1, saved.getStudentId());
        assertEquals("Anand", saved.getStudentName());
        verify(studentDataService, times(1)).createStudent(any(StudentData.class));
    }

    @Test
    void testGetAllStudentData() {
        List<StudentData> students = Arrays.asList(
                student,
                new StudentData(2, "Rahul", "Mumbai", 23, 18000.00)
        );

        when(studentDataService.getAllStudentData()).thenReturn(students);

        List<StudentData> result = studentDataService.getAllStudentData();

        assertEquals(2, result.size());
        assertEquals("Rahul", result.get(1).getStudentName());
        verify(studentDataService, times(1)).getAllStudentData();
    }

    @Test
    void testUpdateStudentData() {
        StudentData updatedStudent = new StudentData(1, "Anand Kumar", "Delhi NCR", 23, 16000.75);

        when(studentDataService.updateStudentData(any(StudentData.class), eq(1))).thenReturn(updatedStudent);

        StudentData result = studentDataService.updateStudentData(updatedStudent, 1);

        assertEquals("Anand Kumar", result.getStudentName());
        assertEquals(16000.75, result.getStudentFees());
        verify(studentDataService, times(1)).updateStudentData(any(StudentData.class), eq(1));
    }

    @Test
    void testGetStudentDataWithId() {
        when(studentDataService.getStudentDataWithId(1)).thenReturn(student);

        StudentData result = studentDataService.getStudentDataWithId(1);

        assertEquals("Anand", result.getStudentName());
        verify(studentDataService, times(1)).getStudentDataWithId(1);
    }

    @Test
    void testDeleteByStudentId() {
        when(studentDataService.deleteByStudentId(1)).thenReturn(student);

        StudentData result = studentDataService.deleteByStudentId(1);

        assertEquals(1, result.getStudentId());
        verify(studentDataService, times(1)).deleteByStudentId(1);
    }

    @Test
    void testAddListOfStudent() {
        List<StudentData> students = Arrays.asList(
                student,
                new StudentData(2, "Rahul", "Mumbai", 23, 18000.00)
        );

        when(studentDataService.addListOfStudent(students)).thenReturn(students);

        List<StudentData> result = studentDataService.addListOfStudent(students);

        assertEquals(2, result.size());
        verify(studentDataService, times(1)).addListOfStudent(students);
    }
}
