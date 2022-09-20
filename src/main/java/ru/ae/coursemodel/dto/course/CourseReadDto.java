package ru.ae.coursemodel.dto.course;

import lombok.Value;
import ru.ae.coursemodel.dto.teacher.TeacherReadDto;

@Value
public class CourseReadDto {
    Long id;
    String title;
    Integer number;
    Double cost;
    TeacherReadDto teacher;
}
