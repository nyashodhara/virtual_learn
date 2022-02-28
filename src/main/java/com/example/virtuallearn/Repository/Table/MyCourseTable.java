package com.example.virtuallearn.Repository.Table;

import com.example.virtuallearn.Entity.Courses;
import com.example.virtuallearn.Entity.MyCourse;
import com.example.virtuallearn.Entity.MyCourses;
import com.example.virtuallearn.Entity.Ongoing;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "mycourse_tbl")
@Data
@NoArgsConstructor
public class MyCourseTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "course_id")
    private long courseId;
    private String username;
    private String course;
    private boolean isongoing;
    private boolean iscompleted;

    public MyCourseTable(long id, long courseId, String username, String course, boolean isongoing, boolean iscompleted) {
        this.id = id;
        this.courseId = courseId;
        this.username = username;
        this.course = course;
        this.isongoing = isongoing;
        this.iscompleted = iscompleted;

    }

    public MyCourses toMyCourses() {
        return new MyCourses(this.id,this.course);
    }
    public Ongoing toOngoing() {
        return new Ongoing(this.id,this.course);
    }

}
