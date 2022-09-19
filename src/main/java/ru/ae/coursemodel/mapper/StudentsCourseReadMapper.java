package ru.ae.coursemodel.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ae.coursemodel.dto.StudentsCourseReadDto;
import ru.ae.coursemodel.entity.StudentsCourse;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StudentsCourseReadMapper implements Mapper<StudentsCourse, StudentsCourseReadDto> {

    private final StudentReadMapper studentReadMapper;
    private final CourseReadMapper courseReadMapper;

    @Override
    public StudentsCourseReadDto map(StudentsCourse object) {
        var studentReadDto = Optional.ofNullable(object.getStudent())
                .map(studentReadMapper::map)
                .orElse(null);
        var courseReadDto = Optional.ofNullable(object.getCourse())
                .map(courseReadMapper::map)
                .orElse(null);

        return new StudentsCourseReadDto(
                object.getId(),
                studentReadDto,
                courseReadDto,
                object.getGrades()
        );
    }
}
