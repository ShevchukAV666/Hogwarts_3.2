package Hogwarts_32.Hogwarts.repository;

import Hogwarts_32.Hogwarts.models.Faculty;
import Hogwarts_32.Hogwarts.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Optional<Faculty> findByNameAndColor(String name, String color);

    List<Faculty> findByColor(String color);

    Collection<Faculty> findByColorOrNameIgnoreCase(String name, String color);

    List<Student> getFacultyStudents(long id);
}
