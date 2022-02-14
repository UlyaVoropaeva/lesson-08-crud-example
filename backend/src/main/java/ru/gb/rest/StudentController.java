package ru.gb.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.persist.Student;
import ru.gb.repository.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository repository;

    @Autowired
    public StudentController(StudentRepository repository) {

        this.repository = repository;
    }

    @GetMapping("/all")
    public List<Student> findAll() {

        return repository.findAll();
    }

    @GetMapping("/{id}/id")
    public Student findById(@PathVariable("id") long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with id = " + id + " not exists"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        if (student.getId() != 0) {
            throw new IllegalArgumentException("Can't create student with id not null");
        }
        return repository.save(student);
    }

    @PutMapping
    public Student update(@RequestBody Student user) {
        if (user.getId() == 0) {
            throw new IllegalArgumentException("Can't update student with id null");
        }
        return repository.save(user);
    }

    @DeleteMapping("/{id}/id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        repository.deleteById(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto notFoundExceptionHandler(ChangeSetPersister.NotFoundException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto notFoundExceptionHandler(IllegalArgumentException ex) {
        return new ErrorDto(ex.getMessage());
    }
}
