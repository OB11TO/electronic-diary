package ru.ae.coursemodel.dto.studentscourse;

import lombok.Value;
import ru.ae.coursemodel.entity.Grades;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Value
public class StudentsCourseCreateDto {
    @NotNull(message = "Student_Id cannot be empty")
    Long studentId;

    @NotNull(message = "Course_Id cannot be empty")
    Long courseId;

    @Size(min = 1, max = 5)
    List<Grades> grades;
}
