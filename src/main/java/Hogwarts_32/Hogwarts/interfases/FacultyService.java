package Hogwarts_32.Hogwarts.interfases;

import Hogwarts_32.Hogwarts.models.Faculty;
import Hogwarts_32.Hogwarts.models.Student;

import java.util.Collection;
import java.util.List;

public interface FacultyService {

     Faculty create(Faculty faculty);

     Faculty read(long id);

     Faculty update(Faculty faculty);

     Faculty delete(long id);

    List<Faculty> readAll(String color);

    Collection<Faculty> getFacultyByNameOrColor(String name, String color);

    List<Student> getFacultyStudents(long id);

}
