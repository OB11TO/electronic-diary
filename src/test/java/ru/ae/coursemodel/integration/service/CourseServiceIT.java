package ru.ae.coursemodel.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.ae.coursemodel.IntegrationTestBase;
import ru.ae.coursemodel.service.CourseService;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class CourseServiceIT extends IntegrationTestBase {

    private final CourseService courseService;

    @Test
    void findAll() {
        var all = courseService.findAll();
        assertThat(all).hasSize(9);
    }
}
