package ru.ae.coursemodel.dto.course;

import lombok.Value;

@Value
public class CourseCreateDto {
    String title;
    Integer number;
    Double cost;
    Long teacherId;
}
