package com.example.virtuallearn.Entity;

import com.example.virtuallearn.Repository.Table.CategoryTable;
import com.example.virtuallearn.Repository.Table.CourseTable;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class Course {
    @NotNull
    private final long id;
    @NotBlank
    private final String course;
    @NotBlank
    private final String overview;
    @NotNull
    private final long categoryId;
    private String category;
    @NotNull
    private final long instructorId;
    private String instructor;


    public CourseTable toCourseTable() {
        return new CourseTable(this.id,this.course,this.overview,this.category,this.categoryId, this.instructor,this.instructorId);
    }
}
