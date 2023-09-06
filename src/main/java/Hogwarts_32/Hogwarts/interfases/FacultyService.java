package Hogwarts_32.Hogwarts.interfases;

import Hogwarts_32.Hogwarts.models.Faculty;

import java.util.List;

public interface FacultyService {

    public Faculty create(Faculty faculty);

    public Faculty read(long id);

    public Faculty update(Faculty faculty);

    public Faculty delete(long id);

    List<Faculty> readAll(String color);
}
