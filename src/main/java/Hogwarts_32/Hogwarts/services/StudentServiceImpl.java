package Hogwarts_32.Hogwarts.services;

import Hogwarts_32.Hogwarts.exception.StudentException;
import Hogwarts_32.Hogwarts.interfases.StudentService;
import Hogwarts_32.Hogwarts.models.Faculty;
import Hogwarts_32.Hogwarts.models.Student;
import Hogwarts_32.Hogwarts.repository.StudentRepository;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.List;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) {

        if (studentRepository.findByNameAndAge(student.getName(), student.getAge()).isPresent()) {
            throw new StudentException("Такой студент уже есть в базе");
        }

        return studentRepository.save(student);
    }

    @Override
    public Student read(long id) {

        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty()) {
            throw new StudentException("Студент не найден");
        }
        return student.get();
    }

    @Override
    public Student update(Student student) {

        if (studentRepository.findById(student.getId()).isEmpty()) {
            throw new StudentException("Студент не найден");
        }
        return studentRepository.save(student);
    }

    @Override
    public Student delete(long id) {

        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty()) {
            throw new StudentException("Студент не найден");
        }
        studentRepository.deleteById(id);
        return student.get();
    }

    @Override
    public List<Student> readAll(int age) {

        return studentRepository.findByAge(age);
    }
    @Override
    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Faculty findStudentByIdFaculty(Long id) {
        return studentRepository.findById(id).map(Student::getFaculty).orElse(null);
    }

}
