package ru.ae.coursemodel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@Tag(name = "StudentsCourse", description = "Студенты на курсах")
@SecurityRequirement(name = "TaskAPISecureScheme")
public class StudentsCourseController {

    private final StudentsCourseService studentsCourseService;

    @Operation(summary = "Получить всех студентов, которые записаны на курсы")
    @GetMapping
    public ResponseEntity<List<StudentsCourseReadDto>> findAll() {
        return ResponseEntity.ok(studentsCourseService.findAll());
    }

    @Operation(summary = "Получить студента, который записан на курс")
    @GetMapping("/{id}")
    public ResponseEntity<StudentsCourseReadDto> findById(@PathVariable Long id) {
        return studentsCourseService.findById(id)
                .map(userChatReadDto -> ResponseEntity.ok().body(userChatReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Добавить студента на курс")
    @PostMapping
    public ResponseEntity<StudentsCourseReadDto> saveStudentsCourse(@Validated @RequestBody StudentsCourseCreateDto studentsCourseCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentsCourseService.saveStudentsCourse(studentsCourseCreateDto));
    }

    @Operation(summary = "Изменить информацию студента на курсе")
    @PutMapping("/{id}")
    public ResponseEntity<StudentsCourseReadDto> updateStudentsCourse(@PathVariable Long id,
                                                                      @Validated @RequestBody StudentsCourseCreateDto studentsCourseCreateDto) {
        return studentsCourseService.updateStudentsCourse(id, studentsCourseCreateDto)
                .map(userChatReadDto -> ResponseEntity.ok().body(userChatReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Удалить студента с курса")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteStudentsCourse(@PathVariable Long id) {
        if (!studentsCourseService.deleteStudentsCourse(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(id);
    }
}
