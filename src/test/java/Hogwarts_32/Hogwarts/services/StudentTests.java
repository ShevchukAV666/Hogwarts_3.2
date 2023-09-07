package Hogwarts_32.Hogwarts.services;

import Hogwarts_32.Hogwarts.interfases.StudentService;
import Hogwarts_32.Hogwarts.models.Student;
import Hogwarts_32.Hogwarts.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentTests {
    @Mock
    private StudentRepository studentRepository;
    private StudentService st;

    @BeforeEach
    public void setUp() {
        st = new StudentServiceImpl(studentRepository);
    }

    @Test
    void read() {
        Optional<Student> optionalStudent = Optional.of(new Student(1L, "Harry", 14));
        Student student = new Student(1L, "Harry", 14);

        when(studentRepository.findById(1L)).thenReturn(optionalStudent);
        assertEquals(student, st.read(1L));
        verify(studentRepository, only()).findById(1L);
    }


    @Test
    void readAll() {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student(1L, "Harry", 23);
        Student student2 = new Student(2L, "Ron", 23);
        studentList.add(student);
        studentList.add(student2);

        when(studentRepository.findByAge(23)).thenReturn(studentList);
        Collection<Student> students = st.readAll(23);
        assertEquals(2, students.size());
    }

}