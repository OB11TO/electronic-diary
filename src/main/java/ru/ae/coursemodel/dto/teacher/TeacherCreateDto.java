package ru.ae.coursemodel.dto.teacher;

import lombok.Value;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Value
@FieldNameConstants
public class TeacherCreateDto {

    @NotEmpty(message = "Name cannot be empty")
    String name;

    @NotEmpty(message = "Address cannot be empty")
    String address;

    @NotEmpty(message = "Phone cannot be empty")
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", //+111 (202) 555-0125
            message = "Incorrect phone number")
    String phone;

    @NotNull(message = "Payment cannot be empty")
    Double payment;
}
