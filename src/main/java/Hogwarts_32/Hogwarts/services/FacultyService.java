package Hogwarts_32.Hogwarts.services;

import Hogwarts_32.Hogwarts.models.Faculty;

import java.util.List;

public interface FacultyService {

    public Faculty addFaculty(Faculty faculty);

    public Faculty findFaculty(long id);

    public Faculty editFaculty(Faculty faculty);

    public Faculty deleteFaculty(long id);

    List<Faculty> readAll(String color);
}
