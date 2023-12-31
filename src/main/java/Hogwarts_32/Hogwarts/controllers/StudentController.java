package Hogwarts_32.Hogwarts.controllers;

import Hogwarts_32.Hogwarts.models.Faculty;
import Hogwarts_32.Hogwarts.models.Student;
import Hogwarts_32.Hogwarts.interfases.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")

public class StudentController {

    public final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentService.create(student);
    }

    @GetMapping("/{id}")
    public Student read(@PathVariable long id) {
        return studentService.read(id);
    }

    @PutMapping
    public Student update(@RequestBody Student student) {
        return studentService.update(student);
    }

    @DeleteMapping("/{id}")
    public Student delete(@PathVariable long id) {
        return studentService.delete(id);
    }

    @GetMapping("/age/{age}")
    public List<Student> readAll(@PathVariable int age) {
        return studentService.readAll(age);
    }

    @GetMapping("/filterStudent")
    public Collection<Student> getStudentBetween(int min, int max) {
        return studentService.findByAgeBetween(min, max);
    }

    @GetMapping("fakulty-student/{id}")
    public Faculty getFakultyByStudent(@PathVariable Long id) {
        return studentService.findStudentByIdFaculty(id);
    }
}
