package ru.ae.coursemodel.dto.report;

import lombok.Value;

@Value
public class TeacherStatisticDto {
    String teacherName;
    Integer studentCounts;
    Double avgCurrentGradeAllCourse;
}
