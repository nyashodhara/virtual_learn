package com.example.virtuallearn.Entity;

import com.example.virtuallearn.Repository.Table.InstructorTable;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class Instructor {
    @NotNull
    private final long id;
    @NotBlank
    private final String instructor;

    public InstructorTable toInstructorTable() {
        return new InstructorTable(this.id,this.instructor);
    }
}
