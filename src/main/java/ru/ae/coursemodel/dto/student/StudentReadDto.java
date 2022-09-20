package ru.ae.coursemodel.dto.student;

import lombok.Value;

@Value
public class StudentReadDto {
    Long id;
    String name;
    String address;
    String phone;
    String email;
    Integer gradeBookNumber;
}
