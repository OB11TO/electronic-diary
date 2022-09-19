package ru.ae.coursemodel.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.ae.coursemodel.IntegrationTestBase;
import ru.ae.coursemodel.repository.StudentsCourseRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class StudentsCourseRepositoryIT extends IntegrationTestBase {

    private final StudentsCourseRepository studentsCourseRepository;

    @Test
    void checkFindAll() {
        var result = studentsCourseRepository.findAll();
        assertThat(result).hasSize(11);
    }
}
