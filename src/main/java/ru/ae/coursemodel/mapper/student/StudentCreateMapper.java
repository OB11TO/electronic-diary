package ru.ae.coursemodel.mapper.student;

import org.springframework.stereotype.Component;
import ru.ae.coursemodel.dto.student.StudentCreateDto;
import ru.ae.coursemodel.entity.Student;
import ru.ae.coursemodel.mapper.Mapper;

@Component
public class StudentCreateMapper implements Mapper<StudentCreateDto, Student> {

    @Override
    public Student map(StudentCreateDto object) {
        Student student = new Student();
        copy(object, student);
        return student;
    }

    @Override
    public Student map(StudentCreateDto fromObject, Student toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(StudentCreateDto fromObject, Student toObject) {
        toObject.setName(fromObject.getName());
        toObject.setAddress(fromObject.getAddress());
        toObject.setPhone(fromObject.getPhone());
        toObject.setEmail(fromObject.getEmail());
        toObject.setGradeBookNumber(fromObject.getGradeBookNumber());
    }
}
