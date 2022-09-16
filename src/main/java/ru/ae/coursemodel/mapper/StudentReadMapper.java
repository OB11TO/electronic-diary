package ru.ae.coursemodel.mapper;

import org.springframework.stereotype.Component;
import ru.ae.coursemodel.dto.StudentReadDto;
import ru.ae.coursemodel.entity.Student;

@Component
public class StudentReadMapper implements Mapper<Student, StudentReadDto> {

    @Override
    public StudentReadDto map(Student object) {
        return new StudentReadDto(
                object.getId(),
                object.getName(),
                object.getAddress(),
                object.getPhone(),
                object.getEmail(),
                object.getGradeBookNumber()
        );
    }
}
