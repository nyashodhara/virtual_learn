package com.example.virtuallearn.Repository.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "chapter_tbl")
@Data
@NoArgsConstructor
public class ChapterTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String course;
    @Column(name = "course_id")
    private long courseId;
    private String chapter;

    public ChapterTable(long id, String course,long courseId, String chapter) {
        this.id = id;
        this.course = course;
        this.courseId = courseId;
        this.chapter = chapter;
    }

}
