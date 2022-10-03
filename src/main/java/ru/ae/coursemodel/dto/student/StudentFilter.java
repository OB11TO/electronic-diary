package ru.ae.coursemodel.dto.student;

import lombok.Value;

@Value
public class StudentFilter {
    String name;
    String phone;
    Integer gradeBookNumber;
}
