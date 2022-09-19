package ru.ae.coursemodel.dto;

import lombok.Value;

@Value
public class StudentsCourseCreateDto {
    Long studentId;
    Long courseId;
    Float grade;
}
