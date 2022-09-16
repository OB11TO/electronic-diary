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
import ru.ae.coursemodel.dto.TeacherCreateDto;
import ru.ae.coursemodel.dto.TeacherReadDto;
import ru.ae.coursemodel.service.TeacherService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherReadDto>> findAll() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherReadDto> findById(@PathVariable Long id) {
        return teacherService.findById(id)
                .map(TeacherReadDto -> ResponseEntity.ok().body(TeacherReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TeacherReadDto> createChat(@RequestBody TeacherCreateDto teacherCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createStudent(teacherCreateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherReadDto> updateChat(@PathVariable Long id, @RequestBody TeacherCreateDto teacherCreateDto) {
        return teacherService.updateStudent(id, teacherCreateDto)
                .map(chatReadDto -> ResponseEntity.ok().body(chatReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteChat(@PathVariable Long id) {
        if(!teacherService.deleteStudent(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(id);
    }
}
