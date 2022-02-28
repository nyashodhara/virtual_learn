package com.example.virtuallearn.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
@AllArgsConstructor
public class Profile {
    private final long phoneNumber;
    private final String fullname;
    private final String username;
    @Email
    private final String email;
    private final String gender;
    private final String occupation;
    @Past
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final Date dateOfBirth;
    @URL
    private final String twitterLink;
    @URL
    private final String facebookLink;
}
