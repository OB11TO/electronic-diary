package ru.ae.coursemodel.mapper.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ae.coursemodel.dto.course.CourseCreateDto;
import ru.ae.coursemodel.entity.Course;
import ru.ae.coursemodel.entity.Teacher;
import ru.ae.coursemodel.mapper.Mapper;
import ru.ae.coursemodel.repository.TeacherRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CourseCreateMapper implements Mapper<CourseCreateDto, Course> {

    private final TeacherRepository teacherRepository;

    @Override
    public Course map(CourseCreateDto object) {
        Course course = new Course();
        copy(object, course);
        return course;
    }

    @Override
    public Course map(CourseCreateDto fromObject, Course toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(CourseCreateDto fromObject, Course toObject) {
        toObject.setTitle(fromObject.getTitle());
        toObject.setNumber(fromObject.getNumber());
        toObject.setCost(fromObject.getCost());
        toObject.setTeacher(getTeacher(fromObject.getTeacherId()));
    }

    public Teacher getTeacher(Long teacherId) {
        return Optional.ofNullable(teacherId)
                .flatMap(teacherRepository::findById)
                .orElse(null);
    }


}
