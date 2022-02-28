package com.example.virtuallearn.Entity;

import com.example.virtuallearn.Repository.Table.ChapterContentTable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChapterContent {
    private final long id;
    private long courseId;
    private String course;
    private final long chapterId;
    private String chapter;
    private final String content;
    private final String link;

    public ChapterContentTable toChapterContentTable() {
        return new ChapterContentTable(this.id,this.courseId, this.course, this.chapterId, this.chapter,this.content,this.link);
    }
}

