package ru.ae.coursemodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ae.coursemodel.entity.Course;
import ru.ae.coursemodel.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select c from Student s " +
            "join s.studentsCourses sc " +
            "join sc.course c " +
            "where s.id = :id")
    List<Course> findByListCourse(Long id);
}
