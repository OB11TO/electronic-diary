package ru.ae.coursemodel.dto;

import lombok.Value;

@Value
public class StudentsCourseReadDto {
    Long id;
    StudentReadDto student;
    CourseReadDto course;
    Float grade;
}
