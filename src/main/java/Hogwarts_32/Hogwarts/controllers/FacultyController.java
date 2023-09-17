package Hogwarts_32.Hogwarts.controllers;

import Hogwarts_32.Hogwarts.models.Faculty;
import Hogwarts_32.Hogwarts.interfases.FacultyService;
import Hogwarts_32.Hogwarts.models.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty create(@RequestBody Faculty faculty) {
        return facultyService.create(faculty);
    }

    @GetMapping("/{id}")
    public Faculty read(@PathVariable long id){
        return facultyService.read(id);
    }

    @PutMapping
    public Faculty update(@RequestBody Faculty faculty) {
        return facultyService.update(faculty);
    }

    @DeleteMapping("/{id}")
    public Faculty delete(@PathVariable long id) {
        return facultyService.delete(id);
    }

    @GetMapping("/color/{color}")
    public List<Faculty> readAll(@PathVariable String color) {
        return facultyService.readAll(color);
    }

    @GetMapping("/filter")
    public Collection<Faculty> getFacultyByNameOrColor(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color) {
        return facultyService.getFacultyByNameOrColor(name, color);
    }

    @GetMapping("/{id}/students")
    public List<Student> getFacultyStudents(@RequestParam Long id) {
        return facultyService.getFacultyStudents(id);
    }

}