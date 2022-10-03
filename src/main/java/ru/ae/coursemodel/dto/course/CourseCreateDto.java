package ru.ae.coursemodel.dto.course;

import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
public class CourseCreateDto {
    @NotEmpty(message = "Name cannot be empty")
    String title;

    @NotNull(message = "Name cannot be empty")
    Integer number;

    @NotNull(message = "Name cannot be empty")
    Double cost;

    @NotNull(message = "Name cannot be empty")
    Long teacherId;
}
