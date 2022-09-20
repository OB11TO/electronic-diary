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
import ru.ae.coursemodel.dto.course.CourseCreateDto;
import ru.ae.coursemodel.dto.course.CourseReadDto;
import ru.ae.coursemodel.service.CourseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseReadDto>> findAll() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseReadDto> findById(@PathVariable Long id) {
        return courseService.findById(id)
                .map(courseReadDto -> ResponseEntity.ok().body(courseReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CourseReadDto> createCourse(@RequestBody CourseCreateDto courseCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(courseCreateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseReadDto> updateCourse(@PathVariable Long id, @RequestBody CourseCreateDto courseCreateDto) {
        return courseService.updateCourse(id, courseCreateDto)
                .map(courseReadDto -> ResponseEntity.ok().body(courseReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteCourse(@PathVariable Long id) {
        if (!courseService.deleteCourse(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(id);
    }
}
