package ru.ae.coursemodel.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ae.coursemodel.dto.CourseReadDto;
import ru.ae.coursemodel.entity.Course;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CourseReadMapper implements Mapper<Course, CourseReadDto> {

    private final TeacherReadMapper teacherReadMapper;

    @Override
    public CourseReadDto map(Course object) {
        var teacherReadDto = Optional.ofNullable(object.getTeacher())
                .map(teacherReadMapper::map)
                .orElse(null);

        return new CourseReadDto(
                object.getId(),
                object.getTitle(),
                object.getNumber(),
                object.getCost(),
                teacherReadDto
        );
    }
}
