package com.leonrv.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.leonrv.models.Student;

public interface IStudentService {


    public List<Student> findAll();
    
    public Student getById(Long id);

    public Student save(Student entity);

    public void delete(Long id);
    
}
