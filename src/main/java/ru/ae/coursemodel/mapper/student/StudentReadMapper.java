package ru.ae.coursemodel.mapper.student;

import org.springframework.stereotype.Component;
import ru.ae.coursemodel.dto.student.StudentReadDto;
import ru.ae.coursemodel.entity.Student;
import ru.ae.coursemodel.mapper.Mapper;

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
