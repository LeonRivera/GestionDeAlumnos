package com.leonrv.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.leonrv.exceptions.StudentNotFoundException;
import com.leonrv.models.Student;
import com.leonrv.repositories.IStudentRepository;

@Service
public class StudentService implements IStudentService{


    @Autowired
    private IStudentRepository repository;

   
    public List<Student> findAll()
    {
      return (List<Student>) repository.findAll();

    }
    public Student getById(Long id){


        return repository.findById(id).orElseThrow(() -> new StudentNotFoundException("No se encontro el estudiante"));
    }

    public Student save(Student entity){
        return repository.save(entity);
    }

    public void delete(Long id){
        if(getById(id)!=null){
            repository.deleteById(id);
        }
    }

   

    
}
