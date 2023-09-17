package Hogwarts_32.Hogwarts.repository;

import Hogwarts_32.Hogwarts.models.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findByStudentId(Long studentId);
}
