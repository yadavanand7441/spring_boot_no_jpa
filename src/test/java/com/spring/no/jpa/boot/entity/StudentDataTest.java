package com.spring.no.jpa.boot.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentDataTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        StudentData student = new StudentData(1, "Anand", "Muzaffarpur", 22, 50000.75);

        assertEquals(1, student.getStudentId());
        assertEquals("Anand", student.getStudentName());
        assertEquals("Muzaffarpur", student.getStudentAddress());
        assertEquals(22, student.getStudentAge());
        assertEquals(50000.75, student.getStudentFees());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        StudentData student = new StudentData();
        student.setStudentId(2);
        student.setStudentName("Komal");
        student.setStudentAddress("Delhi");
        student.setStudentAge(21);
        student.setStudentFees(35000.50);

        assertEquals(2, student.getStudentId());
        assertEquals("Komal", student.getStudentName());
        assertEquals("Delhi", student.getStudentAddress());
        assertEquals(21, student.getStudentAge());
        assertEquals(35000.50, student.getStudentFees());
    }

    @Test
    void testToString() {
        StudentData student = new StudentData(3, "Monu", "Mumbai", 23, 45000.0);

        String toStringResult = student.toString();

        assertTrue(toStringResult.contains("studentId=3"));
        assertTrue(toStringResult.contains("studentName='Monu'"));
        assertTrue(toStringResult.contains("studentAddress='Mumbai'"));
        assertTrue(toStringResult.contains("studentAge=23"));
        assertTrue(toStringResult.contains("studentFees=45000.0"));
    }
}
