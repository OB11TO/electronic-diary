package ru.ae.coursemodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ae.coursemodel.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
