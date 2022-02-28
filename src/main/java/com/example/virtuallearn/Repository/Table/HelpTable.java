package com.example.virtuallearn.Repository.Table;

import com.example.virtuallearn.Entity.Courses;
import com.example.virtuallearn.Entity.Helps;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "help_tbl")
@Data
@NoArgsConstructor
public class HelpTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    @Column(name = "course_id")
    private  long courseId;
    private  String question;
    private String answer;



    public HelpTable(String username, long courseId, String question,String answer) {
        this.username = username;
        this.courseId = courseId;
        this.question = question;
        this.answer = answer;
    }

    public Helps toHelps() {
        return new Helps(this.id,this.courseId, this.question,this.answer);
    }

}
