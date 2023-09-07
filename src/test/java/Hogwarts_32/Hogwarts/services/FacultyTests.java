package Hogwarts_32.Hogwarts.services;

import Hogwarts_32.Hogwarts.interfases.FacultyService;
import Hogwarts_32.Hogwarts.models.Faculty;
import Hogwarts_32.Hogwarts.repository.FacultyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class FacultyTests {
    @Mock
    private FacultyRepository facultyRepository;
    private FacultyService facul;

    @BeforeEach
    public void setUp() {
        facul = new FacultyServiceImpl(facultyRepository);
    }

    @Test
    void read() { //+
        Optional<Faculty> optionalFaculty = Optional.of(new Faculty(1L, "Gryffindor", "Red"));
        Faculty faculty = new Faculty(1L, "Gryffindor", "Red");

        when(facultyRepository.findById(1L)).thenReturn(optionalFaculty);
        assertEquals(faculty, facul.read(1L));
        verify(facultyRepository, only()).findById(1L);
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

}
