package ru.ae.coursemodel.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import ru.ae.coursemodel.dto.student.StudentFilter;
import ru.ae.coursemodel.entity.Student;
import ru.ae.coursemodel.repository.FilterStudentRepository;
import ru.ae.coursemodel.repository.querydsl.QPredicates;

import javax.persistence.EntityManager;
import java.util.List;

import static ru.ae.coursemodel.entity.QStudent.student;

@RequiredArgsConstructor
public class FilterStudentRepositoryImpl implements FilterStudentRepository {

    private final EntityManager entityManager;

    @Override
    public List<Student> findAllByFilter(StudentFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.getName(), student.name::containsIgnoreCase)
                .add(filter.getPhone(), student.phone::containsIgnoreCase)
                .add(filter.getGradeBookNumber(), student.gradeBookNumber::eq)
                .build();

        return new JPAQuery<Student>(entityManager)
                .select(student)
                .from(student)
                .where(predicate)
                .fetch();
    }
}
