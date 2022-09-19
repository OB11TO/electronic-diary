package ru.ae.coursemodel.dto;

import lombok.Value;
import ru.ae.coursemodel.entity.Grades;

import java.util.List;

@Value
public class StudentsCourseCreateDto {
    Long studentId;
    Long courseId;
    List<Grades> grades;
}
