package com.example.virtuallearn.Repository.Table;

import com.example.virtuallearn.Entity.Instructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "instructor_tbl")
@Data
@NoArgsConstructor
public class InstructorTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String instructor;
    @Column(name = "course_id")
    private long courseId;

    public InstructorTable(String instructor, long courseId ) {
        this.instructor = instructor;
        this.courseId = courseId;
    }


    public Instructor toInstructor(){
        return new Instructor(this.instructor,this.courseId);
    }
}
