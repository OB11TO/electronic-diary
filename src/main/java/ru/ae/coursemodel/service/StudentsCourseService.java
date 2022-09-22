package ru.ae.coursemodel.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.ae.coursemodel.dto.studentscourse.StudentsCourseCreateDto;
import ru.ae.coursemodel.dto.studentscourse.StudentsCourseReadDto;
import ru.ae.coursemodel.mapper.studentcourse.StudentsCourseCreateMapper;
import ru.ae.coursemodel.mapper.studentcourse.StudentsCourseReadMapper;
import ru.ae.coursemodel.repository.StudentsCourseRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentsCourseService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(StudentsCourseService.class);
    private final StudentsCourseRepository studentsCourseRepository;
    private final StudentsCourseReadMapper studentsCourseReadMapper;
    private final StudentsCourseCreateMapper studentsCourseCreateMapper;

    public List<StudentsCourseReadDto> findAll() {
        log.info("Get all StudentsCourse");
        return studentsCourseRepository.findAll().stream()
                .map(studentsCourseReadMapper::map)
                .collect(toList());
    }

    public Optional<StudentsCourseReadDto> findById(Long id) {
        log.info("Get StudentsCourse with id : {}", id);
        return studentsCourseRepository.findById(id)
                .map(studentsCourseReadMapper::map);
    }

    @Transactional
    public StudentsCourseReadDto saveStudentsCourse(StudentsCourseCreateDto studentsCourseCreateDto) {
        log.info("Create StudentsCourse : {}", studentsCourseCreateDto);
        return Optional.of(studentsCourseCreateDto)
                .map(studentsCourseCreateMapper::map)
                .map(studentsCourseRepository::save)
                .map(studentsCourseReadMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Optional<StudentsCourseReadDto> updateStudentsCourse(Long id, StudentsCourseCreateDto studentsCourseCreateDto) {
        log.info("Update StudentsCourse with id : {}, data : {}", id, studentsCourseCreateDto);
        return studentsCourseRepository.findById(id)
                .map(entity -> studentsCourseCreateMapper.map(studentsCourseCreateDto, entity))
                .map(studentsCourseRepository::saveAndFlush)
                .map(studentsCourseReadMapper::map);
    }

    @Transactional
    public boolean deleteStudentsCourse(Long id) {
        log.info("Remove StudentsCourse with id : {}", id);
        return studentsCourseRepository.findById(id)
                .map(entity -> {
                    studentsCourseRepository.delete(entity);
                    studentsCourseRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
