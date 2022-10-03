package ru.ae.coursemodel.repository;

import ru.ae.coursemodel.dto.student.StudentFilter;
import ru.ae.coursemodel.entity.Student;

import java.util.List;

public interface FilterStudentRepository {

    List<Student> findAllByFilter(StudentFilter filter);
}
