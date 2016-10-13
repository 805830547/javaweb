package com.springmvc.myutil.hssfexcel;

import java.util.ArrayList;

public class Student {
    private String studentNumber;
    private String studentName;
    private String studentSex;
    private String studentAge;

    public Student() {
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
    public Student parserDate(ArrayList<String> studentString) {
        Student stu = new Student();
        stu.setStudentNumber(studentString.get(0));
        stu.setStudentName(studentString.get(1));
        stu.setStudentSex(studentString.get(2));
        stu.setStudentAge(studentString.get(3));
        return stu;
    }
}
