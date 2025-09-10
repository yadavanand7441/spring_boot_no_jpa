package com.spring.no.jpa.boot.entity;

public class StudentData {

    private int studentId;
    private String studentName;
    private String studentAddress;
    private int studentAge;
    private double studentFees;

    public StudentData(int studentId, String studentName, String studentAddress, int studentAge, double studentFees) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAddress = studentAddress;
        this.studentAge = studentAge;
        this.studentFees = studentFees;
    }

    public StudentData() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public double getStudentFees() {
        return studentFees;
    }

    public void setStudentFees(double studentFees) {
        this.studentFees = studentFees;
    }

    @Override
    public String toString() {
        return "StudentData{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentAddress='" + studentAddress + '\'' +
                ", studentAge=" + studentAge +
                ", studentFees=" + studentFees +
                '}';
    }
}
