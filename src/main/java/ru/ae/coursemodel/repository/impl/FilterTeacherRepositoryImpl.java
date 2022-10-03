package ru.ae.coursemodel.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import ru.ae.coursemodel.dto.teacher.TeacherFilter;
import ru.ae.coursemodel.entity.Student;
import ru.ae.coursemodel.entity.Teacher;
import ru.ae.coursemodel.repository.FilterTeacherRepository;
import ru.ae.coursemodel.repository.querydsl.QPredicates;

import javax.persistence.EntityManager;
import java.util.List;

import static ru.ae.coursemodel.entity.QTeacher.teacher;

@RequiredArgsConstructor
public class FilterTeacherRepositoryImpl implements FilterTeacherRepository {

    private final EntityManager entityManager;

    @Override
    public List<Teacher> findAllByFilter(TeacherFilter filter) {
        if (filter != null) {
            var predicate = QPredicates.builder()
                    .add(filter.getName(), teacher.name::containsIgnoreCase)
                    .add(filter.getPhone(), teacher.phone::containsIgnoreCase)
                    .add(filter.getPayment(), teacher.payment::eq)
                    .build();

            return new JPAQuery<Student>(entityManager)
                    .select(teacher)
                    .from(teacher)
                    .where(predicate)
                    .fetch();
        }
        return new JPAQuery<Student>(entityManager)
                .select(teacher)
                .from(teacher)
                .fetch();
    }
}
