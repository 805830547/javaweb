package com.springmvc.api.persistence;

import java.util.List;

import com.springmvc.api.model.Student;

public interface StudentDao {

    public void setStudent(Student student);

    public List<Student> getStudent();
}
