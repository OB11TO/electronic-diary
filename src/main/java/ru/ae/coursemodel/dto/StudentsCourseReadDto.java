package ru.ae.coursemodel.dto;

import lombok.Value;
import ru.ae.coursemodel.entity.Grades;

import java.util.List;

@Value
public class StudentsCourseReadDto {
    Long id;
    StudentReadDto student;
    CourseReadDto course;
    List<Grades> grades;
}
