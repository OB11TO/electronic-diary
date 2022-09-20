package ru.ae.coursemodel.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.ae.coursemodel.IntegrationTestBase;
import ru.ae.coursemodel.repository.StudentRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class StudentRepositoryIT extends IntegrationTestBase {

    private final static Long STUDENT_ID_1 = 1L;
    private final static Long STUDENT_ID_4 = 4L;
    private final static Long STUDENT_ID_5 = 5L;
    private final static Long COURSE_ID_1 = 9L;
    private final StudentRepository studentRepository;

    @Test
    void checkFindByAvgCurrentGrade() {
        var byCurrentGrade = studentRepository.findByAvgCurrentGrade(STUDENT_ID_4);
        System.out.println(byCurrentGrade);
    }

    @Test
    void checkFindByFinalGradeOfCourse() {
        var avgGrade = studentRepository.findByFinalGradeOfCourse(STUDENT_ID_1, COURSE_ID_1);
        System.out.println(avgGrade);
    }

    @Test
    void checkFindAlListCourse() {
        var result = studentRepository.findByListCourse(STUDENT_ID_1);
        assertThat(result).hasSize(2);
    }
}
