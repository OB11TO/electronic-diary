package ru.ae.coursemodel.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ae.coursemodel.dto.teacher.TeacherCreateDto;
import ru.ae.coursemodel.dto.teacher.TeacherFilter;
import ru.ae.coursemodel.dto.teacher.TeacherReadDto;
import ru.ae.coursemodel.mapper.teacher.TeacherCreateMapper;
import ru.ae.coursemodel.mapper.teacher.TeacherReadMapper;
import ru.ae.coursemodel.repository.TeacherRepository;
import ru.ae.coursemodel.repository.querydsl.QPredicates;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static ru.ae.coursemodel.entity.QTeacher.teacher;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherReadMapper teacherReadMapper;
    private final TeacherCreateMapper teacherCreateMapper;

    public List<TeacherReadDto> findAll(TeacherFilter filter) {
        log.info("Get all teachers");
        return teacherRepository.findAllByFilter(filter).stream()
                .map(teacherReadMapper::map)
                .collect(toList());
    }

    public Page<TeacherReadDto> findAll(TeacherFilter filter, Pageable pageable) {
        var predicate = QPredicates.builder()
                .add(filter.getName(), teacher.name::containsIgnoreCase)
                .add(filter.getPhone(), teacher.phone::containsIgnoreCase)
                .add(filter.getPayment(), teacher.payment::eq)
                .build();
        log.info("Get all teachers + filter + pageable");
        return teacherRepository.findAll(predicate, pageable)
                .map(teacherReadMapper::map);
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
    @PreAuthorize("hasAuthority('ADMIN')")
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
