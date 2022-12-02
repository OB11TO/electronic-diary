package ru.ae.coursemodel.dto.user;

import lombok.Value;
import lombok.experimental.FieldNameConstants;
import ru.ae.coursemodel.entity.Role;

@Value
@FieldNameConstants
public class UserCreateDto {

    String username;

    String rawPassword;

    String firstname;

    String lastname;

    Role role;
}

