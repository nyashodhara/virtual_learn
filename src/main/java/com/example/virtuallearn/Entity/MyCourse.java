package com.example.virtuallearn.Entity;

import com.example.virtuallearn.Repository.Table.CourseTable;
import com.example.virtuallearn.Repository.Table.MyCourseTable;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class MyCourse {
    @NotNull
    private final long id;
    @NotNull
    private final long courseId;
    private String username;
    private String course;
    private boolean isongoing;
    private final boolean iscompleted;

    public MyCourseTable toMyCourseTable() {
        return new MyCourseTable(this.id,this.courseId,this.username,this.course,this.isongoing,this.iscompleted);
    }
}

