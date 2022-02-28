package com.example.virtuallearn.Repository.Table;

import com.example.virtuallearn.Entity.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_tbl")
@Data
@NoArgsConstructor
public class UserTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "phone_number")
    private long phoneNumber;
    private String password;
    private String fullname;
    private String username;
    private String email;
    private String gender;
    private String occupation;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "twitter_link")
    private String twitterLink;
    @Column(name = "facebook_link")
    private String facebookLink;
    private long otp;
    private String oldPassword;
    private String savedPassword;
    private String role;


    public UserTable(long id,long phoneNumber,String password,String fullname,String username,String email,String gender,String occupation,Date dateOfBirth,String twitterLink,String facebookLink, String role) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.occupation = occupation;
        this.dateOfBirth = dateOfBirth;
        this.twitterLink = twitterLink;
        this.facebookLink = facebookLink;
        this.role = role;
    }

    public Profile toProfile() {
        return new Profile(this.phoneNumber, this.fullname, this.username, this.email, this.gender, this.occupation, this.dateOfBirth, this.twitterLink, this.facebookLink);
    }
}
