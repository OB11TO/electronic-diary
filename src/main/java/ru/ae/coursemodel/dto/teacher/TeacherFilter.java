package ru.ae.coursemodel.dto.teacher;

import lombok.Value;

@Value
public class TeacherFilter {
    String name;
    String phone;
    Double payment;
}
