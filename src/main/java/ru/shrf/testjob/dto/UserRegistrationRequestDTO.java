package ru.shrf.testjob.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequestDTO {

    @NotNull
    @Email(message = "Invalid email format")
    private String email;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username can only contain Latin characters and numbers")
    private String username;
    private String name;

}
