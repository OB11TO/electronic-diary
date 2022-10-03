package ru.ae.coursemodel.dto.student;

import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
public class StudentCreateDto {
    @NotEmpty(message = "Name cannot be empty")
    String name;

    @NotEmpty(message = "Address cannot be empty")
    String address;

    @NotEmpty(message = "Phone cannot be empty")
    String phone;

    @NotEmpty(message = "Email cannot be empty")
    String email;

    @NotNull(message = "Grade Book Number cannot be empty")
    Integer gradeBookNumber;
}
