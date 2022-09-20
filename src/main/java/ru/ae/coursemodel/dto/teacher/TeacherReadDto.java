package ru.ae.coursemodel.dto.teacher;

import lombok.Value;

@Value
public class TeacherReadDto {
    Long id;
    String name;
    String address;
    String phone;
    Double payment;
}
