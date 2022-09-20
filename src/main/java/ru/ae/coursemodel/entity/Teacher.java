package ru.ae.coursemodel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ae.coursemodel.dto.report.TeacherStatisticDto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@NamedNativeQuery(
        name = "Teacher.findAllTeacherStatistics",
        resultSetMapping = "Mapping.TeacherStatisticDto",
        query = "select t.name teacherName, " +
                "count(distinct (s.id)) studentCounts, " +
                "round(avg(g.grade), 2) avgCurrentGradeAllCourse " +
                "from teacher t " +
                "         join course c on t.id = c.teacher_id " +
                "         join students_course sc on c.id = sc.course_id " +
                "         join student s on s.id = sc.student_id " +
                "         join grades g on sc.id = g.students_course_id " +
                "group by t.name")
@SqlResultSetMapping(
        name = "Mapping.TeacherStatisticDto",
        classes = @ConstructorResult(targetClass = TeacherStatisticDto.class,
                columns = {
                        @ColumnResult(name = "teacherName", type = String.class),
                        @ColumnResult(name = "studentCounts", type = Integer.class),
                        @ColumnResult(name = "avgCurrentGradeAllCourse", type = Double.class)
                }))
public class Teacher implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phone;

    private Double payment;

}
