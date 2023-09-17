package Hogwarts_32.Hogwarts.services;

import Hogwarts_32.Hogwarts.exception.FacultyException;
import Hogwarts_32.Hogwarts.interfases.FacultyService;
import Hogwarts_32.Hogwarts.models.Faculty;
import Hogwarts_32.Hogwarts.models.Student;
import Hogwarts_32.Hogwarts.repository.FacultyRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class FacultyServiceTests {

    @Mock
    private FacultyRepository facultyRepository;
    @Mock
    private StudentRepository studentRepository;
    private FacultyService facul;

    @InjectMocks
    FacultyServiceImpl underTest;

    Faculty faculty = new Faculty(1L, "Griffindor", "red");

    Student student = new Student(1L, "Harry", 13);


    @BeforeEach
     public void setUp() {
     facul = new FacultyServiceImpl(facultyRepository, studentRepository);
    }


    @Test
    public void create() { //+
        when(facultyRepository.findByNameAndColor(faculty.getName(), faculty.getColor()))
                .thenReturn(Optional.empty());
        when(facultyRepository.save(faculty)).thenReturn(faculty);
        Faculty result = underTest.create(faculty);
        assertEquals(faculty, result);
        assertEquals((Long) 1L, result.getId());
    }

    @Test
    public void createFacultyException() { //+
        when(facultyRepository.findByNameAndColor(faculty.getName(), faculty.getColor()))
                .thenReturn(Optional.of(faculty));
        FacultyException result = assertThrows(FacultyException.class, () -> underTest.create(faculty));
        assertEquals("Такой факультет уже есть", result.getMessage());
    }

    @Test
    public void read() { //+
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));
        assertEquals(faculty, facul.read(1L));
        verify(facultyRepository, only()).findById(1L);
    }

    @Test
    public void readFacultyException() { //+
        when(facultyRepository.findById(1L)).thenReturn(Optional.empty());
        FacultyException result = assertThrows(FacultyException.class, () -> underTest.read(1L));
        assertEquals("Факультет не найден", result.getMessage());
    }

    @Test
    public void update() { //+
        when(facultyRepository.findById(faculty.getId())).thenReturn(Optional.of(faculty));
        when(facultyRepository.save(faculty)).thenReturn(faculty);
        Faculty result = underTest.update(faculty);
        assertEquals(faculty, result);
    }

    @Test
    public void updateFacultyException() { //+
        when(facultyRepository.findById(faculty.getId())).thenReturn(Optional.empty());
        FacultyException result = assertThrows(FacultyException.class, () -> underTest.update(faculty));
        assertEquals("Факультет не найден", result.getMessage());
    }

    @Test
    public void delete() { //+
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));
        doNothing().when(facultyRepository).deleteById(1L);
        Faculty result = underTest.delete(1L);
        assertEquals(faculty, result);
    }

    @Test
    void deleteFacultyException() { //+
        when(facultyRepository.findById(1L)).thenReturn(Optional.empty());
        FacultyException result = assertThrows(FacultyException.class, () -> underTest.read(1L));
        assertThrows(FacultyException.class, () -> underTest.read(1L));
        assertEquals("Факультет не найден", result.getMessage());
    }

    @Test
    void readAll() { //+
        List<Faculty> facultyList = new ArrayList<>();
        Faculty faculty1 = new Faculty(1L, "Gryffindor", "Red");
        Faculty faculty2 = new Faculty(2L, "Gryffindor", "Red");
        facultyList.add(faculty1);
        facultyList.add(faculty2);

        when(facultyRepository.findByColor("Red")).thenReturn(facultyList);
        Collection<Faculty> faculties = facul.readAll("Red");
        assertEquals(2, faculties.size());
    }

    @Test
    void getFacultiesByNameOrColour() { //+
        List<Faculty> facultyList = new ArrayList<>();
        Faculty faculty1 = new Faculty(1L, "Gryffindor", "Red and gold");
        Faculty faculty2 = new Faculty(2L, "gryffindor", "red and gold");
        facultyList.add(faculty1);
        facultyList.add(faculty2);

        when(facultyRepository.findByColorOrName("Gryffindor", "Red and gold")).thenReturn(facultyList);
        Collection<Faculty> faculties = facul.getFacultyByNameOrColor("Gryffindor","Red and gold");
        assertEquals(2, faculties.size());
        verify(facultyRepository, only()).findByColorOrName("Gryffindor", "Red and gold");
    }

    @Test
    void getFacultyStudents() { //+
        when(facultyRepository.existsById(1L)).thenReturn(true);
        when(studentRepository.findByFaculty_id(1L)).thenReturn(List.of(student));
        List<Student> result = underTest.getFacultyStudents(1L);
        assertEquals(List.of(student), result);
    }

    @Test
    void getFacultyStudentsException() { //+
        when(facultyRepository.existsById(1L)).thenReturn(false);
        FacultyException result = assertThrows(FacultyException.class, () -> underTest.getFacultyStudents(1L));
        assertThrows(FacultyException.class, () -> underTest.getFacultyStudents(1L));
        assertEquals("Такого факультета нет", result.getMessage());
    }

}

