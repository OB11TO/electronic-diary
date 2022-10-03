package ru.ae.coursemodel.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.ae.coursemodel.IntegrationTestBase;
import ru.ae.coursemodel.dto.student.StudentFilter;
import ru.ae.coursemodel.repository.StudentRepository;
import ru.ae.coursemodel.service.StudentService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class StudentRepositoryIT extends IntegrationTestBase {

    private final static Long STUDENT_ID_1 = 1L;
    private final static Long STUDENT_ID_4 = 4L;
    private final static Long STUDENT_ID_5 = 5L;
    private final static Long COURSE_ID_1 = 9L;
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @Test
    void checkQueryDslFilter2() {
        var filter = new StudentFilter(
                "a", null, null
        );
        var students = studentService.findAll(filter);
        assertThat(students).hasSize(2);
    }

    @Test
    void checkQueryDslFilter() {
        var filter = new StudentFilter(
                "a", null, null
        );
        var students = studentRepository.findAllByFilter(filter);
        assertThat(students).hasSize(2);
    }

    @Test
    void checkFindByAvgCurrentGrade() {
        var byCurrentGrade = studentRepository.findByAvgCurrentGrade(STUDENT_ID_4);
        assertTrue(byCurrentGrade.isPresent());
        byCurrentGrade.ifPresent(grade -> assertEquals(grade, 3.75));
    }

    @Test
    void checkFindByFinalGradeOfCourse() {
        var avgGrade = studentRepository.findByFinalGradeOfCourse(STUDENT_ID_1, COURSE_ID_1);
        assertTrue(avgGrade.isPresent());
        avgGrade.ifPresent(grade -> assertEquals(grade, 4.5));
    }

    @Test
    void checkFindAlListCourse() {
        var result = studentRepository.findByListCourse(STUDENT_ID_1);
        assertThat(result).hasSize(2);
    }

    @Test
    void checkFindAll() {
        var result = studentRepository.findAll();
        assertThat(result).hasSize(5);
    }
}
