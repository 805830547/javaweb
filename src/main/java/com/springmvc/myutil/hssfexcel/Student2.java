package com.springmvc.myutil.hssfexcel;

import java.util.List;

public class Student2 {
    private String studentNumber;
    private String studentName;
    private String studentSex;
    private String studentAge;

    public Student2() {
        // TODO Auto-generated constructor stub
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public String getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(String studentAge) {
        this.studentAge = studentAge;
    }

    // reflect to creat a new object
    public Student2 parserDate(List<String> studentString) {
        
        studentNumber = studentString.get(0).trim();
        studentName = studentString.get(1).trim();
        studentSex = studentString.get(2).trim();
        studentAge = studentString.get(3).trim();
        
        return this;
    }
}
