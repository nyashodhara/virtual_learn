package com.example.virtuallearn.Entity;

import com.example.virtuallearn.Repository.Table.InstructorTable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Instructor {
    private final String instructor;
    private final long courseId;

    public InstructorTable toInstructorTable() {
        return new  InstructorTable(this.instructor,this.courseId);
    }
}
