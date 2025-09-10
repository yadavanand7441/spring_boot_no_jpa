package com.spring.no.jpa.boot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.no.jpa.boot.entity.StudentData;
import com.spring.no.jpa.boot.services.impl.StudentDataServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentDataServiceImpl studentDataServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddStudentData() throws Exception {
        StudentData student = new StudentData(1, "Anand", "Muzaffarpur", 22, 50000.75);

        Mockito.when(studentDataServiceImpl.createStudent(any(StudentData.class))).thenReturn(student);

        mockMvc.perform(post("/rest/api/no/jpa/student/save/studentData")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentName").value("Anand"))
                .andExpect(jsonPath("$.studentFees").value(50000.75));
    }

    @Test
    void testGetAllStudentData() throws Exception {
        List<StudentData> students = Arrays.asList(
                new StudentData(1, "Anand", "Muzaffarpur", 22, 50000.75),
                new StudentData(2, "Komal", "Delhi", 21, 35000.50)
        );

        Mockito.when(studentDataServiceImpl.getAllStudentData()).thenReturn(students);

        mockMvc.perform(get("/rest/api/no/jpa/student/studentDataList"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].studentName").value("Anand"))
                .andExpect(jsonPath("$[1].studentName").value("Komal"));
    }

    @Test
    void testUpdateStudentRecord() throws Exception {
        StudentData updated = new StudentData(1, "Anand", "Mumbai", 23, 60000.0);

        Mockito.when(studentDataServiceImpl.updateStudentData(any(StudentData.class), eq(1)))
                .thenReturn(updated);

        mockMvc.perform(put("/rest/api/no/jpa/student/update/{studentId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentAddress").value("Mumbai"))
                .andExpect(jsonPath("$.studentFees").value(60000.0));
    }

    @Test
    void testGetSingleStudentWithId() throws Exception {
        StudentData student = new StudentData(1, "Monu", "Mumbai", 23, 45000.0);

        Mockito.when(studentDataServiceImpl.getStudentDataWithId(1)).thenReturn(student);

        mockMvc.perform(get("/rest/api/no/jpa/student/fetch/{studentId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentName").value("Monu"))
                .andExpect(jsonPath("$.studentAddress").value("Mumbai"));
    }

    @Test
    void testDeleteStudentById() throws Exception {
        StudentData deleted = new StudentData(2, "Komal", "Delhi", 21, 35000.50);

        Mockito.when(studentDataServiceImpl.deleteByStudentId(2)).thenReturn(deleted);

        mockMvc.perform(delete("/rest/api/no/jpa/student/delete/{studentId}", 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentName").value("Komal"))
                .andExpect(jsonPath("$.studentAddress").value("Delhi"));
    }

    @Test
    void testAddListOfStudent() throws Exception {
        List<StudentData> students = Arrays.asList(
                new StudentData(3, "Pintu", "Patna", 25, 40000.0),
                new StudentData(4, "Ravi", "Lucknow", 24, 37000.0)
        );

        Mockito.when(studentDataServiceImpl.addListOfStudent(any(List.class))).thenReturn(students);

        mockMvc.perform(post("/rest/api/no/jpa/student/listOfStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(students)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].studentName").value("Pintu"))
                .andExpect(jsonPath("$[1].studentName").value("Ravi"));
    }
}
