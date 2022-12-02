package ru.ae.coursemodel.http.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
import ru.ae.coursemodel.dto.PageResponse;
import ru.ae.coursemodel.dto.course.CourseReadDto;
import ru.ae.coursemodel.dto.student.StudentCreateDto;
import ru.ae.coursemodel.dto.student.StudentFilter;
import ru.ae.coursemodel.dto.student.StudentReadDto;
import ru.ae.coursemodel.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@Tag(name = "Student", description = "студенты")
public class StudentRestController {

    private final StudentService studentService;

    @Operation(summary = "Получить среднюю оценку по всем курсам у студента по его id")
    @GetMapping("/student/{idStudent}/average-grade")
    public ResponseEntity<Double> findByAvgCurrentGrade(@PathVariable Long idStudent) {
        return studentService.findByAvgCurrentGrade(idStudent)
                .map(grade -> ResponseEntity.ok().body(grade))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Получить финальную оценку за конкретный курс")
    @GetMapping("/student/{idStudent}/course/{idCourse}/final-grade")
    public ResponseEntity<Double> findByFinalGradeOfCourse(@PathVariable Long idStudent,
                                                           @PathVariable Long idCourse) {
        return studentService.findByFinalGradeOfCourse(idStudent, idCourse)
                .map(grade -> ResponseEntity.ok().body(grade))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Получить список курсов на которых учится студент")
    @GetMapping("/student{id}/courses")
    public ResponseEntity<List<CourseReadDto>> findAllListCourse(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findAllListCourse(id));
    }

    @Operation(summary = "Получить всех студентов")
    @GetMapping
    public ResponseEntity<PageResponse<StudentReadDto>> findAll(Pageable pageable) {
        var page = studentService.findAll(pageable);
        return ResponseEntity.ok(PageResponse.of(page));
    }

    @Operation(summary = "Получить всех студентов с фильтрацией")
    @GetMapping("/filter")
    public List<StudentReadDto> findAll(StudentFilter filter) {
        return studentService.findAll(filter);
    }

    @Operation(summary = "Получить студента по его id")
    @GetMapping("/{id}")
    public ResponseEntity<StudentReadDto> findById(@PathVariable Long id) {
        return studentService.findById(id)
                .map(studentReadDto -> ResponseEntity.ok().body(studentReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Сохранить нового студента")
    @PostMapping
    public ResponseEntity<StudentReadDto> createStudent(@Validated @RequestBody StudentCreateDto studentCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(studentCreateDto));
    }

    @Operation(summary = "Изменить данные о конкретном студенте по его id")
    @PutMapping("/{id}")
    public ResponseEntity<StudentReadDto> updateStudent(@PathVariable Long id,
                                                        @Validated @RequestBody StudentCreateDto studentCreateDto) {
        return studentService.updateStudent(id, studentCreateDto)
                .map(chatReadDto -> ResponseEntity.ok().body(chatReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Удалить конкретного студента по его id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteStudent(@PathVariable Long id) {
        if (!studentService.deleteStudent(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(id);
    }

}
