package ru.ae.coursemodel.dto.studentscourse;

import lombok.Value;
import ru.ae.coursemodel.dto.course.CourseReadDto;
import ru.ae.coursemodel.dto.student.StudentReadDto;
import ru.ae.coursemodel.entity.Grades;

import java.util.List;

@Value
public class StudentsCourseReadDto {
    Long id;
    StudentReadDto student;
    CourseReadDto course;
    List<Grades> grades;
}
