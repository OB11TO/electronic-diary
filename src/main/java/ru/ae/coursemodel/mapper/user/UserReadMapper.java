package ru.ae.coursemodel.mapper.user;

import org.springframework.stereotype.Component;
import ru.ae.coursemodel.dto.user.UserReadDto;
import ru.ae.coursemodel.entity.User;
import ru.ae.coursemodel.mapper.Mapper;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Override
    public UserReadDto map(User user) {
        return new UserReadDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstname(),
                user.getLastname(),
                user.getRole()
        );
    }
}
