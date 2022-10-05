package ru.ae.coursemodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.ae.coursemodel.entity.Course;
import ru.ae.coursemodel.entity.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends
        JpaRepository<Student, Long>,
        FilterStudentRepository,
        QuerydslPredicateExecutor<Student> {

    @Query("select c " +
            "from Student s " +
            "       join s.studentsCourses sc " +
            "       join sc.course c " +
            "where s.id = :id")
    List<Course> findByListCourse(Long id);

    @Query("select avg(g.grade) " +
            "from Student s " +
            "       join s.studentsCourses sc " +
            "       join sc.course c " +
            "       join sc.grades g " +
            "where s.id = :idStudent and c.id = :idCourse")
    Optional<Double> findByFinalGradeOfCourse(Long idStudent, Long idCourse);

    @Query(value = "select round(avg(gg),2) " +
            "from (select s.id student,avg(g.grade) gg " +
            "      from student s " +
            "               join students_course sc on s.id = sc.student_id " +
            "               join course c on c.id = sc.course_id " +
            "               join grades g on sc.id = g.students_course_id " +
            "      group by c.id, s.id " +
            "      order by s.id) ss " +
            "where ss.student = :idStudent", nativeQuery = true)
    Optional<Double> findByAvgCurrentGrade(Long idStudent);

}
