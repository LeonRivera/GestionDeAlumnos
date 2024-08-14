package com.leonrv.datamock;

import java.util.List;

import com.leonrv.models.Student;

public class DataMockProvider {

    public static List<Student> students = List.of(
            new Student(1L, "Leon", "Rivera", "leonriv2@gmail.com", 23),
            new Student(2L, "Andres", "Rivera", "ae@gmail.com", 24));

    public static Student studentMock() {
        return new Student(2L, "Andres", "Rivera", "ae@gmail.com", 24);
    }

    public static Student newStudentMock() {
        return new Student(100L, "Jose", "Dominguez", "ae@gmail.com", 24);
    }
}
