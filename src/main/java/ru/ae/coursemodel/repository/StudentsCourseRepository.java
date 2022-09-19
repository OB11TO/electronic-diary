package ru.ae.coursemodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ae.coursemodel.entity.StudentsCourse;

@Repository
public interface StudentsCourseRepository extends JpaRepository<StudentsCourse, Long> {
}
