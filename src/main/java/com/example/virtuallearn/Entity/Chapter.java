package com.example.virtuallearn.Entity;

import com.example.virtuallearn.Repository.Table.ChapterTable;
import com.example.virtuallearn.Repository.Table.CourseTable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Chapter {
    private final long id;
    private final String chapter;
    private final long courseId;
    private String course;



    public ChapterTable toChapterTable() {
        return new ChapterTable(this.id, this.course,this.courseId, this.chapter);
    }
}


