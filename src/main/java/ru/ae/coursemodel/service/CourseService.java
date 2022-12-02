package ru.ae.coursemodel.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ae.coursemodel.dto.course.CourseCreateDto;
import ru.ae.coursemodel.dto.course.CourseReadDto;
import ru.ae.coursemodel.mapper.course.CourseCreateMapper;
import ru.ae.coursemodel.mapper.course.CourseReadMapper;
import ru.ae.coursemodel.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseReadMapper courseReadMapper;
    private final CourseCreateMapper courseCreateMapper;

    public List<CourseReadDto> findAll() {
        log.info("Get all courses");
        return courseRepository.findAll().stream()
                .map(courseReadMapper::map)
                .collect(toList());
    }

    public Optional<CourseReadDto> findById(Long id) {
        log.info("Get course with id: {}", id);
        return courseRepository.findById(id)
                .map(courseReadMapper::map);
    }

    @Transactional
    public CourseReadDto createCourse(CourseCreateDto courseCreateDto) {
        log.info("Create course : {}", courseCreateDto);
        return Optional.of(courseCreateDto)
                .map(courseCreateMapper::map)
                .map(courseRepository::save)
                .map(courseReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<CourseReadDto> updateCourse(Long id, CourseCreateDto courseCreateDto) {
        log.info("Update course with id : {}, data:: {}", id, courseCreateDto);
        return courseRepository.findById(id)
                .map(entity -> courseCreateMapper.map(courseCreateDto, entity))
                .map(courseRepository::saveAndFlush)
                .map(courseReadMapper::map);

    }

    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean deleteCourse(Long id) {
        log.info("Remove course with id : {}", id);
        return courseRepository.findById(id)
                .map(entity -> {
                    courseRepository.delete(entity);
                    courseRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
