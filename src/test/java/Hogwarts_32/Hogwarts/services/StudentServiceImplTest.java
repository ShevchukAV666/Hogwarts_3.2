package Hogwarts_32.Hogwarts.services;

import Hogwarts_32.Hogwarts.exception.StudentException;
import Hogwarts_32.Hogwarts.models.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceImplTest {
    StudentServiceImpl underTest = new StudentServiceImpl();
    Student student = new Student(0L, "Artem", 29);

    @Test
    void create_studentNotMap_studentAddedReturned() {
        Student result = underTest.create(student);

        assertEquals(student, result);
        assertEquals(1, result.getId());
    }

    @Test
    void create_studentNotMap_throwException(){

        underTest.create(student);

        StudentException result =
                assertThrows(StudentException.class,
                        () -> underTest.create(student));
        assertEquals("Такой студент уже есть", result.getMessage());
    }
}