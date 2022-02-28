package com.example.virtuallearn.Repository.Table;

import com.example.virtuallearn.Entity.ChapterContent;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "chapter_content_tbl")
@Data
@NoArgsConstructor
public class ChapterContentTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    @Column(name = "course_id")
    private  long courseId;
    private  String course;
    @Column(name = "chapter_id")
    private  long chapterId;
    private  String chapter;
    private  String content;
    private  String link;

    public ChapterContentTable(long id,long courseId,String course,long chapterId,String chapter,String content, String link ) {
        this.id = id;
        this.courseId = courseId;
        this.course = course;
        this.chapterId = chapterId;
        this.chapter = chapter;
        this.content = content;
        this.link = link;
    }

    public ChapterContent toChapterContent() {
        return new ChapterContent(this.id, this.courseId, this.course,this.chapterId,this.chapter,this.content,this.link);
    }

}
