package com.example.virtuallearn.Entity;


import com.example.virtuallearn.Repository.Table.UserTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    @NotNull
    private final long id;
    private final long phoneNumber;
    private final long otp;
    private final String password;
    private final String fullname;
    private final String username;
    @Email
    private final String email;
    private final String gender;
    private final String occupation;
    private final Date dateOfBirth;
    @URL
    private final String twitterLink;
    @URL
    private final String facebookLink;
    private final String oldPassword;
    private String role;



    public UserTable toUserTable() {
        return new UserTable(this.id,this.phoneNumber, this.password,this.fullname,this.username,this.email,this.gender,this.occupation,this.dateOfBirth,this.twitterLink,this.facebookLink,this.role);
    }
}
