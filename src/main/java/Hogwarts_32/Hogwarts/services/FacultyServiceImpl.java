package Hogwarts_32.Hogwarts.services;

import Hogwarts_32.Hogwarts.exception.FacultyException;
import Hogwarts_32.Hogwarts.interfases.FacultyService;
import Hogwarts_32.Hogwarts.models.Faculty;
import Hogwarts_32.Hogwarts.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl  implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
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
}
