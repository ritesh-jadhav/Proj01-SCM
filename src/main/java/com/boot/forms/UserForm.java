package com.boot.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {
    @NotBlank(message = "Username is required!")
    @Size(min = 3, max = 10, message = "User name must be of 3 to 10 characters!")
    private String name;
    @NotBlank(message = "Email id should not be blank!")
    @Email(message = "Invalid Email address!")
    private String email;
    @Size(min = 8, max = 12, message = "Phone number should be in the range of 8 to 12 digits")
    private String phoneNumber;
    @NotBlank(message = "About not should be empty!")
    private String about;
    @Size(min = 6, message = "Password should be at least 6 characters!")
    private String password;
}
