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
import ru.ae.coursemodel.dto.studentscourse.StudentsCourseCreateDto;
import ru.ae.coursemodel.dto.studentscourse.StudentsCourseReadDto;
import ru.ae.coursemodel.service.StudentsCourseService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students_courses")
@RequiredArgsConstructor
public class StudentsCourseController {

    private final StudentsCourseService studentsCourseService;

    @GetMapping
    public ResponseEntity<List<StudentsCourseReadDto>> findAll() {
        return ResponseEntity.ok(studentsCourseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentsCourseReadDto> findById(@PathVariable Long id) {
        return studentsCourseService.findById(id)
                .map(userChatReadDto -> ResponseEntity.ok().body(userChatReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<StudentsCourseReadDto> saveUserChat(@RequestBody StudentsCourseCreateDto studentsCourseCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentsCourseService.saveStudentsCourse(studentsCourseCreateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentsCourseReadDto> updateUserChat(@PathVariable Long id, @RequestBody StudentsCourseCreateDto studentsCourseCreateDto) {
        return studentsCourseService.updateStudentsCourse(id, studentsCourseCreateDto)
                .map(userChatReadDto -> ResponseEntity.ok().body(userChatReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUserChat(@PathVariable Long id) {
        if (!studentsCourseService.deleteStudentsCourse(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(id);
    }
}
