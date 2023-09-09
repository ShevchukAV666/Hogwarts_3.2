package Hogwarts_32.Hogwarts.repository;

import Hogwarts_32.Hogwarts.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends JpaRepository <Faculty, Long> {

    Optional<Faculty> findByNameAndColor(String name, String color);

    List<Faculty> findByColor (String color);
}
