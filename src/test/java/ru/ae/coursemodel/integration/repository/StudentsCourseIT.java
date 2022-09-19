package ru.ae.coursemodel.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.ae.coursemodel.IntegrationTestBase;
import ru.ae.coursemodel.repository.StudentsCourseRepository;

@RequiredArgsConstructor
public class StudentsCourseIT extends IntegrationTestBase {

    private final StudentsCourseRepository studentsCourseRepository;

    @Test
    void checkFindAll() {
        var all = studentsCourseRepository.findAll();
        all.forEach(System.out::println);
    }
}
