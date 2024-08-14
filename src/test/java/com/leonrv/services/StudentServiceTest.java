package com.leonrv.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.leonrv.datamock.DataMockProvider;
import com.leonrv.models.Student;
import com.leonrv.repositories.IStudentRepository;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private IStudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenCallingFindAll_returnAllStudents(){

        when(studentRepository.findAll()).thenReturn(DataMockProvider.students);

        List<Student> result = studentService.findAll();

        assertNotNull(result);
        assertEquals("Leon", result.get(0).getNombre());

    }

    @Test
    void whenCallingFindById_returnAStudent(){
        when(studentRepository.findById(1L)).thenReturn(Optional.of(DataMockProvider.students.get(0)));

        Student result = studentService.getById(1L);

        assertNotNull(result);
        assertEquals("Rivera", result.getApellido());
    }

    @Test
    void givenAStudent_whenCallingSave_thenSave(){
        Student student = DataMockProvider.newStudentMock();

        studentRepository.save(student);

        ArgumentCaptor<Student> studArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(this.studentRepository).save(any(Student.class));
        verify(this.studentRepository).save(studArgumentCaptor.capture());

        assertEquals(100L, studArgumentCaptor.getValue().getId());
    }

    @Test
    void givenAStudent_whenDeletingIt_thenIsNoPresent(){

        Long id = 2L;

        studentRepository.deleteById(id);

        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(this.studentRepository).deleteById(longArgumentCaptor.capture());

        assertEquals(id, longArgumentCaptor.getValue());

    }


    @Test
    void givenAStudent_whenUpdating_thenReturnStudent(){
        ArgumentCaptor<Student> studArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        Student student = DataMockProvider.newStudentMock();

        studentRepository.save(student);
        

        student.setNombre("Nuevo nombre");
        studentRepository.save(student);

        verify(this.studentRepository, times(2)).save(studArgumentCaptor.capture());

        assertEquals("Nuevo nombre", studArgumentCaptor.getValue().getNombre());
    
    }



    
}
