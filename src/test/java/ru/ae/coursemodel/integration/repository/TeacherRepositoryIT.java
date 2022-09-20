package ru.ae.coursemodel.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.ae.coursemodel.IntegrationTestBase;
import ru.ae.coursemodel.repository.TeacherRepository;

@RequiredArgsConstructor
public class TeacherRepositoryIT extends IntegrationTestBase {

    private final TeacherRepository teacherRepository;

    @Test
    void checkTeacherStatistics() {
        var statistics = teacherRepository.findAllTeacherStatistics();
        statistics.forEach(System.out::println);
    }
}
