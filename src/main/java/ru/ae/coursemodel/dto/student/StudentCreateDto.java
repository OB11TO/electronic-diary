package ru.ae.coursemodel.dto.student;

import lombok.Value;

@Value
public class StudentCreateDto {
    String name;
    String address;
    String phone;
    String email;
    Integer gradeBookNumber;
}
