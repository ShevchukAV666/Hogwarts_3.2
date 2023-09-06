package Hogwarts_32.Hogwarts.repository;

import Hogwarts_32.Hogwarts.models.Faculty;
import Hogwarts_32.Hogwarts.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository  extends JpaRepository <Student, Long> {

    Optional<Student> findByNameAndAge(String name, int age);

    List<Student> findByAge(int age);
}
