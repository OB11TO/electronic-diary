package ru.ae.coursemodel.repository;

import ru.ae.coursemodel.dto.teacher.TeacherFilter;
import ru.ae.coursemodel.entity.Teacher;

import java.util.List;

public interface FilterTeacherRepository {

    List<Teacher> findAllByFilter(TeacherFilter filter);
}
