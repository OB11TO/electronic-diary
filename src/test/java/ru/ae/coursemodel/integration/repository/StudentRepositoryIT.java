package ru.ae.coursemodel.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.ae.coursemodel.IntegrationTestBase;
import ru.ae.coursemodel.repository.StudentRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class StudentRepositoryIT extends IntegrationTestBase {

    private final static Long STUDENT_ID_1 = 1L;
    private final StudentRepository studentRepository;

    @Test
    void checkFindAlListCourse() {
        var result = studentRepository.findByListCourse(STUDENT_ID_1);
        assertThat(result).hasSize(2);
    }
}
