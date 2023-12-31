package Hogwarts_32.Hogwarts.services;

import Hogwarts_32.Hogwarts.exception.FacultyException;
import Hogwarts_32.Hogwarts.interfases.FacultyService;
import Hogwarts_32.Hogwarts.models.Faculty;
import Hogwarts_32.Hogwarts.models.Student;
import Hogwarts_32.Hogwarts.repository.FacultyRepository;
import Hogwarts_32.Hogwarts.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl  implements FacultyService {

    private final FacultyRepository facultyRepository;

    private final StudentRepository studentRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Faculty create(Faculty faculty) {
        if (facultyRepository.findByNameAndColor(faculty.getName(), faculty.getColor()).isPresent()) {
            throw new FacultyException("Такой факультет уже есть");
        }
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty read(long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isEmpty()) {
            throw new FacultyException("Факультет не найден");
        }
        return faculty.get();
    }

    @Override
    public Faculty update(Faculty faculty) {
        if (facultyRepository.findById(faculty.getId()).isEmpty()) {
            throw new FacultyException("Факультет не найден");
        }
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty delete(long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isEmpty()) {
            throw new FacultyException("Факультет не найден");
        }
        facultyRepository.deleteById(id);
        return faculty.get();
    }

    @Override
    public List<Faculty> readAll(String color) {
        return facultyRepository.findByColor(color);
    }

    @Override
    public Collection<Faculty> getFacultyByNameOrColor(String name, String color) {
        return facultyRepository.findByColorOrName(name, color);
    }

    @Override
    public List<Student> getFacultyStudents(Long id) {
       if (!facultyRepository.existsById(id)) {
            throw new FacultyException("Такого факультета нет");
       }
        return studentRepository.findByFaculty_id(id);
    }
}

