package Hogwarts_32.Hogwarts.services;

import Hogwarts_32.Hogwarts.exception.FacultyException;
import Hogwarts_32.Hogwarts.models.Faculty;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl  implements FacultyService {

    private final Map<Long, Faculty> facultyMap = new HashMap<>();

    private long count;

    public Faculty addFaculty(Faculty faculty) {
        if (facultyMap.containsValue(faculty)) {
            throw new FacultyException("Такой факультет уже есть");
        }
        faculty.setId(++count);
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        if (!facultyMap.containsKey(id)) {
            throw new FacultyException("Факультет не найден");
        }
        return facultyMap.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (!facultyMap.containsKey(faculty.getId())) {
            throw new FacultyException("Факультет не найден");
        }
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id) {
        Faculty faculty = facultyMap.remove(id);
        if(faculty==null) {
            throw new FacultyException("Факультет не найден");
        }
        return faculty;
    }

    public List<Faculty> readAll(String color ){

        return facultyMap.values().stream()
                .filter(st -> st.getColor() == color)
                .collect(Collectors.toUnmodifiableList());
    }
}
