package ru.ae.coursemodel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ae.coursemodel.dto.StudentCreateDto;
import ru.ae.coursemodel.dto.StudentReadDto;
import ru.ae.coursemodel.mapper.StudentCreateMapper;
import ru.ae.coursemodel.mapper.StudentReadMapper;
import ru.ae.coursemodel.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentReadMapper studentReadMapper;
    private final StudentCreateMapper studentCreateMapper;

    public List<StudentReadDto> findAll() {
        return studentRepository.findAll().stream()
                .map(studentReadMapper::map)
                .collect(toList());
    }

    public Optional<StudentReadDto> findById(Long id) {
        return studentRepository.findById(id)
                .map(studentReadMapper::map);
    }

    @Transactional
    public StudentReadDto createStudent(StudentCreateDto studentCreateDto) {
        return Optional.of(studentCreateDto)
                .map(studentCreateMapper::map)
                .map(studentRepository::save)
                .map(studentReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<StudentReadDto> updateStudent(Long id, StudentCreateDto studentCreateDto) {
        return studentRepository.findById(id)
                .map(entity -> studentCreateMapper.map(studentCreateDto, entity))
                .map(studentRepository::saveAndFlush)
                .map(studentReadMapper::map);
    }

    @Transactional
    public boolean deleteStudent(Long id) {
        return studentRepository.findById(id)
                .map(entity -> {
                    studentRepository.delete(entity);
                    studentRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
