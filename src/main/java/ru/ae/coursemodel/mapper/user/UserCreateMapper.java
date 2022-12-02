package ru.ae.coursemodel.mapper.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.ae.coursemodel.dto.user.UserCreateDto;
import ru.ae.coursemodel.entity.User;
import ru.ae.coursemodel.mapper.Mapper;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserCreateDto, User> {

    private final PasswordEncoder passwordEncoder;


    @Override
    public User map(UserCreateDto fromObject, User toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    @Override
    public User map(UserCreateDto object) {
        User user = new User();
        copy(object, user);

        return user;
    }

    private void copy(UserCreateDto object, User user) {
        user.setUsername(object.getUsername());
        user.setFirstname(object.getFirstname());
        user.setLastname(object.getLastname());
        user.setRole(object.getRole());

        Optional.ofNullable(object.getRawPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);
    }
}
