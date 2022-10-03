package ru.ae.coursemodel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import ru.ae.coursemodel.dto.teacher.TeacherCreateDto;
import ru.ae.coursemodel.dto.teacher.TeacherFilter;
import ru.ae.coursemodel.dto.teacher.TeacherReadDto;
import ru.ae.coursemodel.service.TeacherService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teachers")
@Tag(name = "Teacher", description = "профессора")
public class TeacherController {

    private final TeacherService teacherService;

    @Operation(summary = "Получить всех профессоров")
    @GetMapping
    public ResponseEntity<List<TeacherReadDto>> findAll(TeacherFilter filter) {
        return ResponseEntity.ok(teacherService.findAll(filter));
    }

    @Operation(summary = "Получить профессора по id")
    @GetMapping("/{id}")
    public ResponseEntity<TeacherReadDto> findById(@PathVariable Long id) {
        return teacherService.findById(id)
                .map(TeacherReadDto -> ResponseEntity.ok().body(TeacherReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Сохранить нового профессора")
    @PostMapping
    public ResponseEntity<TeacherReadDto> createTeacher(@RequestBody TeacherCreateDto teacherCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(teacherCreateDto));
    }

    @Operation(summary = "Изменить данные о профессоре по id")
    @PutMapping("/{id}")
    public ResponseEntity<TeacherReadDto> updateTeacher(@PathVariable Long id, @RequestBody TeacherCreateDto teacherCreateDto) {
        return teacherService.updateTeacher(id, teacherCreateDto)
                .map(chatReadDto -> ResponseEntity.ok().body(chatReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Удалить профессора по его id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteTeacher(@PathVariable Long id) {
        if (!teacherService.deleteTeacher(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(id);
    }
}
