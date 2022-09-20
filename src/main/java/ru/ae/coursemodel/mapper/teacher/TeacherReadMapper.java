package ru.ae.coursemodel.mapper.teacher;

import org.springframework.stereotype.Component;
import ru.ae.coursemodel.dto.teacher.TeacherReadDto;
import ru.ae.coursemodel.entity.Teacher;
import ru.ae.coursemodel.mapper.Mapper;

@Component
public class TeacherReadMapper implements Mapper<Teacher, TeacherReadDto> {

    @Override
    public TeacherReadDto map(Teacher object) {
        return new TeacherReadDto(
                object.getId(),
                object.getName(),
                object.getAddress(),
                object.getPhone(),
                object.getPayment()
        );
    }
}
