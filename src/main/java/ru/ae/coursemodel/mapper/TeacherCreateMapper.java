package ru.ae.coursemodel.mapper;

import org.springframework.stereotype.Component;
import ru.ae.coursemodel.dto.TeacherCreateDto;
import ru.ae.coursemodel.entity.Teacher;

@Component
public class TeacherCreateMapper implements Mapper<TeacherCreateDto, Teacher>{


    @Override
    public Teacher map(TeacherCreateDto object) {
        Teacher teacher = new Teacher();
        copy(object, teacher);
        return teacher;
    }

    @Override
    public Teacher map(TeacherCreateDto fromObject, Teacher toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(TeacherCreateDto fromObject, Teacher toObject) {
        toObject.setName(fromObject.getName());
        toObject.setAddress(fromObject.getAddress());
        toObject.setPhone(fromObject.getPhone());
        toObject.setPayment(fromObject.getPayment());
    }
}
