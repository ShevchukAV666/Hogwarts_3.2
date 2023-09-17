package Hogwarts_32.Hogwarts.repository;

import Hogwarts_32.Hogwarts.models.Faculty;
import Hogwarts_32.Hogwarts.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository  extends JpaRepository <Student, Long> {

    Optional<Student> findByNameAndAge(String name, int age);

    List<Student> findByAge(int age);

    List<Student>findByFaculty_id(long facultyId);

    Collection<Student> findByAgeBetween(int min, int max);
}
