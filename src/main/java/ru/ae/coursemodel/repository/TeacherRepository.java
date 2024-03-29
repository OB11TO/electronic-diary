package ru.ae.coursemodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.ae.coursemodel.dto.report.TeacherStatisticDto;
import ru.ae.coursemodel.entity.Teacher;

import java.util.List;

@Repository
public interface TeacherRepository extends
        JpaRepository<Teacher, Long>,
        FilterTeacherRepository,
        QuerydslPredicateExecutor<Teacher> {

    @Query(nativeQuery = true)
    List<TeacherStatisticDto> findAllTeacherStatistics();
}
