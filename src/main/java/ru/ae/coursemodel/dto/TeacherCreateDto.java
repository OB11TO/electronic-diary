package ru.ae.coursemodel.dto;

import lombok.Value;

@Value
public class TeacherCreateDto {
    String name;
    String address;
    String phone;
    Double payment;
}
