package ru.ae.coursemodel.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ae.coursemodel.dto.course.CourseReadDto;
import ru.ae.coursemodel.dto.student.StudentCreateDto;
import ru.ae.coursemodel.dto.student.StudentFilter;
import ru.ae.coursemodel.dto.student.StudentReadDto;
import ru.ae.coursemodel.mapper.course.CourseReadMapper;
import ru.ae.coursemodel.mapper.student.StudentCreateMapper;
import ru.ae.coursemodel.mapper.student.StudentReadMapper;
import ru.ae.coursemodel.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentReadMapper studentReadMapper;
    private final StudentCreateMapper studentCreateMapper;
    private final CourseReadMapper courseReadMapper;

    public Optional<Double> findByAvgCurrentGrade(Long idStudent) {
        log.info("Get the current average score for all courses a student with id : {}", idStudent);
        return studentRepository.findByAvgCurrentGrade(idStudent);
    }

    public Optional<Double> findByFinalGradeOfCourse(Long idStudent, Long idCourse) {
        log.info("Get a final grade from a student with id : {} on the course with id :{}", idStudent, idCourse);
        return studentRepository.findByFinalGradeOfCourse(idStudent, idCourse);
    }

    public List<CourseReadDto> findAllListCourse(Long id) {
        log.info("Get list of courses attended by the student with id : {}", id);
        return studentRepository.findByListCourse(id).stream()
                .map(courseReadMapper::map)
                .collect(toList());
    }

    public List<StudentReadDto> findAll() {
        log.info("Get all students");
        return studentRepository.findAll().stream()
                .map(studentReadMapper::map)
                .collect(toList());
    }

    public List<StudentReadDto> findAll(StudentFilter studentFilter) {
        log.info("Get all filter students");
        return studentRepository.findAllByFilter(studentFilter).stream()
                .map(studentReadMapper::map)
                .collect(toList());
    }

    public Optional<StudentReadDto> findById(Long id) {
        log.info("Get student with id : {}", id);
        return studentRepository.findById(id)
                .map(studentReadMapper::map);
    }

    @Transactional
    public StudentReadDto createStudent(StudentCreateDto studentCreateDto) {
        log.info("Create student : {}", studentCreateDto);
        return Optional.of(studentCreateDto)
                .map(studentCreateMapper::map)
                .map(studentRepository::save)
                .map(studentReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<StudentReadDto> updateStudent(Long id, StudentCreateDto studentCreateDto) {
        log.info("Update student with id : {}, data : {}", id, studentCreateDto);
        return studentRepository.findById(id)
                .map(entity -> studentCreateMapper.map(studentCreateDto, entity))
                .map(studentRepository::saveAndFlush)
                .map(studentReadMapper::map);
    }

    @Transactional
    public boolean deleteStudent(Long id) {
        log.info("Remove student with id : {}", id);
        return studentRepository.findById(id)
                .map(entity -> {
                    studentRepository.delete(entity);
                    studentRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
