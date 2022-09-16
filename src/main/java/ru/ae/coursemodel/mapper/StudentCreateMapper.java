package ru.ae.coursemodel.mapper;

import org.springframework.stereotype.Component;
import ru.ae.coursemodel.dto.StudentCreateDto;
import ru.ae.coursemodel.entity.Student;

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
