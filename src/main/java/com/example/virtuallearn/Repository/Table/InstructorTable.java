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

    public InstructorTable(long id, String instructor) {
        this.id = id;
        this.instructor = instructor;
    }

    public Instructor toInstructor() {
        return new Instructor(this.id, this.instructor);
    }
}
