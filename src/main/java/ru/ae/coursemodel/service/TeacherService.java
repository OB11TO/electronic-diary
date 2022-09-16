package ru.ae.coursemodel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ae.coursemodel.dto.TeacherCreateDto;
import ru.ae.coursemodel.dto.TeacherReadDto;
import ru.ae.coursemodel.mapper.TeacherCreateMapper;
import ru.ae.coursemodel.mapper.TeacherReadMapper;
import ru.ae.coursemodel.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherReadMapper teacherReadMapper;
    private final TeacherCreateMapper teacherCreateMapper;

    public List<TeacherReadDto> findAll() {
        return teacherRepository.findAll().stream()
                .map(teacherReadMapper::map)
                .collect(toList());
    }

    public Optional<TeacherReadDto> findById(Long id) {
        return teacherRepository.findById(id)
                .map(teacherReadMapper::map);
    }

    @Transactional
    public TeacherReadDto createStudent(TeacherCreateDto teacherCreateDto) {
        return Optional.of(teacherCreateDto)
                .map(teacherCreateMapper::map)
                .map(teacherRepository::save)
                .map(teacherReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<TeacherReadDto> updateStudent(Long id, TeacherCreateDto teacherCreateDto) {
        return teacherRepository.findById(id)
                .map(entity -> teacherCreateMapper.map(teacherCreateDto, entity))
                .map(teacherRepository::saveAndFlush)
                .map(teacherReadMapper::map);
    }

    @Transactional
    public boolean deleteStudent(Long id) {
        return teacherRepository.findById(id)
                .map(entity -> {
                    teacherRepository.delete(entity);
                    teacherRepository.flush();
                    return true;
                })
                .orElse(false);
    }

}
