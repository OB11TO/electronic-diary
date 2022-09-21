package ru.ae.coursemodel.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ae.coursemodel.dto.teacher.TeacherCreateDto;
import ru.ae.coursemodel.dto.teacher.TeacherReadDto;
import ru.ae.coursemodel.mapper.teacher.TeacherCreateMapper;
import ru.ae.coursemodel.mapper.teacher.TeacherReadMapper;
import ru.ae.coursemodel.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherReadMapper teacherReadMapper;
    private final TeacherCreateMapper teacherCreateMapper;

    public List<TeacherReadDto> findAll() {
        log.info("Get all teachers");
        return teacherRepository.findAll().stream()
                .map(teacherReadMapper::map)
                .collect(toList());
    }

    public Optional<TeacherReadDto> findById(Long id) {
        log.info("Get teacher with id : {}", id);
        return teacherRepository.findById(id)
                .map(teacherReadMapper::map);
    }

    @Transactional
    public TeacherReadDto createTeacher(TeacherCreateDto teacherCreateDto) {
        log.info("Create teacher : {}", teacherCreateDto);
        return Optional.of(teacherCreateDto)
                .map(teacherCreateMapper::map)
                .map(teacherRepository::save)
                .map(teacherReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<TeacherReadDto> updateTeacher(Long id, TeacherCreateDto teacherCreateDto) {
        log.info("Update teacher with id : {}, data : {}", id, teacherCreateDto);
        return teacherRepository.findById(id)
                .map(entity -> teacherCreateMapper.map(teacherCreateDto, entity))
                .map(teacherRepository::saveAndFlush)
                .map(teacherReadMapper::map);
    }

    @Transactional
    public boolean deleteTeacher(Long id) {
        log.info("Remove teacher with id : {}", id);
        return teacherRepository.findById(id)
                .map(entity -> {
                    teacherRepository.delete(entity);
                    teacherRepository.flush();
                    return true;
                })
                .orElse(false);
    }

}
