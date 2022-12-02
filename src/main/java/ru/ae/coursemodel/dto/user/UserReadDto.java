package ru.ae.coursemodel.dto.user;

import lombok.Value;
import ru.ae.coursemodel.entity.Role;

@Value
public class UserReadDto {

    Long id;
    String username;
    String password;
    String firstname;
    String lastname;
    Role role;
}
