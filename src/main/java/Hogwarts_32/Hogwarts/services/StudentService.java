package Hogwarts_32.Hogwarts.services;

import Hogwarts_32.Hogwarts.models.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface StudentService {

    public Student create (Student student);

    Student read (long id);

    Student update (Student student);

    Student delete (long id);

    List<Student> readAll(int age);

}
