package ru.ae.coursemodel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ae.coursemodel.dto.CourseCreateDto;
import ru.ae.coursemodel.dto.CourseReadDto;
import ru.ae.coursemodel.mapper.CourseCreateMapper;
import ru.ae.coursemodel.mapper.CourseReadMapper;
import ru.ae.coursemodel.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseReadMapper courseReadMapper;
    private final CourseCreateMapper courseCreateMapper;

    public List<CourseReadDto> findAll() {
        return courseRepository.findAll().stream()
                .map(courseReadMapper::map)
                .collect(toList());
    }

    public Optional<CourseReadDto> findById(Long id) {
        return courseRepository.findById(id)
                .map(courseReadMapper::map);
    }

    @Transactional
    public CourseReadDto createCourse(CourseCreateDto courseCreateDto) {
        return Optional.of(courseCreateDto)
                .map(courseCreateMapper::map)
                .map(courseRepository::save)
                .map(courseReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<CourseReadDto> updateCourse(Long id, CourseCreateDto courseCreateDto) {
        return courseRepository.findById(id)
                .map(entity -> courseCreateMapper.map(courseCreateDto, entity))
                .map(courseRepository::saveAndFlush)
                .map(courseReadMapper::map);

    }

    @Transactional
    public boolean deleteCourse(Long id) {
        return courseRepository.findById(id)
                .map(entity -> {
                    courseRepository.delete(entity);
                    courseRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
