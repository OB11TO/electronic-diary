package ru.ae.coursemodel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.ae.coursemodel.dto.StudentCreateDto;
import ru.ae.coursemodel.dto.StudentReadDto;
import ru.ae.coursemodel.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentReadDto>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentReadDto> findById(@PathVariable Long id) {
        return studentService.findById(id)
                .map(studentReadDto -> ResponseEntity.ok().body(studentReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<StudentReadDto> createChat(@RequestBody StudentCreateDto studentCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(studentCreateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentReadDto> updateChat(@PathVariable Long id, @RequestBody StudentCreateDto studentCreateDto) {
        return studentService.updateStudent(id, studentCreateDto)
                .map(chatReadDto -> ResponseEntity.ok().body(chatReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteChat(@PathVariable Long id) {
        if(!studentService.deleteStudent(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(id);
    }

}
