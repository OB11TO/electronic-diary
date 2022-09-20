package ru.ae.coursemodel.dto;

import lombok.Value;

@Value
public class TeacherStatisticDto {
    String teacherName;
    Integer studentCounts;
    Double avgCurrentGradeAllCourse;
}
