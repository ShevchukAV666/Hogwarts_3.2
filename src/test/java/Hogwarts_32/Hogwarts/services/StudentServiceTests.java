package Hogwarts_32.Hogwarts.services;

import Hogwarts_32.Hogwarts.exception.FacultyException;
import Hogwarts_32.Hogwarts.exception.StudentException;
import Hogwarts_32.Hogwarts.interfases.StudentService;
import Hogwarts_32.Hogwarts.models.Faculty;
import Hogwarts_32.Hogwarts.models.Student;
import Hogwarts_32.Hogwarts.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class StudentServiceTests {

    @Mock
    private StudentRepository studentRepository;
    private StudentService st;

    @InjectMocks
    StudentServiceImpl underTest2;

    Student student = new Student(1L, "Harry", 14);

    @BeforeEach
    public void setUp() {
        st = new StudentServiceImpl(studentRepository);
    }

    @Test
    public void create() { //+
        when(studentRepository.findByNameAndAge(student.getName(), student.getAge()))
                .thenReturn(Optional.empty());
        when(studentRepository.save(student)).thenReturn(student);
        Student result = underTest2.create(student);
        assertEquals(student, result);
        assertEquals((Long) 1L, result.getId());
    }

    @Test
    public void createStudentException() { //+
        when(studentRepository.findByNameAndAge(student.getName(), student.getAge()))
                .thenReturn(Optional.of(student));
        StudentException result = assertThrows(StudentException.class, () -> underTest2.create(student));
        assertEquals("Такой студент уже есть в базе", result.getMessage());
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
    public void readStudentException() { //+
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        StudentException result = assertThrows(StudentException.class, () -> underTest2.read(1L));
        assertEquals("Студент не найден", result.getMessage());
    }

    @Test
    public void update() { //+
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(studentRepository.save(student)).thenReturn(student);
        Student result = underTest2.update(student);
        assertEquals(student, result);
    }

    @Test
    public void updateStudentException() { //+
        when(studentRepository.findById(student.getId())).thenReturn(Optional.empty());
        StudentException result = assertThrows(StudentException.class, () -> underTest2.update(student));
        assertEquals("Студент не найден", result.getMessage());
    }

    @Test
    public void delete() { //+
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        doNothing().when(studentRepository).deleteById(1L);
        Student result = underTest2.delete(1L);
        assertEquals(student, result);
    }

    @Test
    void deleteStudentException() { //+
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        StudentException result = assertThrows(StudentException.class, () -> underTest2.read(1L));
        assertThrows(StudentException.class, () -> underTest2.read(1L));
        assertEquals("Студент не найден", result.getMessage());
    }

    @Test
    void readAll() { //+
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