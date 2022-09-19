package ru.ae.coursemodel.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ae.coursemodel.dto.StudentsCourseCreateDto;
import ru.ae.coursemodel.entity.Course;
import ru.ae.coursemodel.entity.Student;
import ru.ae.coursemodel.entity.StudentsCourse;
import ru.ae.coursemodel.repository.CourseRepository;
import ru.ae.coursemodel.repository.StudentRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StudentsCourseCreateMapper implements Mapper<StudentsCourseCreateDto, StudentsCourse> {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public StudentsCourse map(StudentsCourseCreateDto object) {
        StudentsCourse studentsCourse = new StudentsCourse();
        copy(object, studentsCourse);
        return studentsCourse;
    }

    @Override
    public StudentsCourse map(StudentsCourseCreateDto fromObject, StudentsCourse toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(StudentsCourseCreateDto fromObject, StudentsCourse toObject) {
        toObject.setStudent(getStudent(fromObject.getStudentId()));
        toObject.setCourse(getCourse(fromObject.getCourseId()));
        toObject.setGrades(fromObject.getGrades());
    }

    private Course getCourse(Long courseId) {
        return Optional.ofNullable(courseId)
                .flatMap(courseRepository::findById)
                .orElseThrow();
    }

    private Student getStudent(Long studentId) {
        return Optional.ofNullable(studentId)
                .flatMap(studentRepository::findById)
                .orElseThrow();
    }
}
