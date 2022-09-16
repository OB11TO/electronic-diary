package ru.ae.coursemodel.dto;

import lombok.Value;

@Value
public class CourseReadDto {
    Long id;
    String title;
    Integer number;
    Double cost;
    TeacherReadDto teacher;
}
