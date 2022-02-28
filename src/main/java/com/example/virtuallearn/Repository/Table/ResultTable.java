package com.example.virtuallearn.Repository.Table;

import com.example.virtuallearn.Entity.Results;
import com.example.virtuallearn.Entity.Tests;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "result_tbl")
@Data
@NoArgsConstructor
public class ResultTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    @Column(name = "course_id")
    private long courseId;
    @Column(name = "chapter_id")
    private long chapterId;
    private String question;
    @Column(name = "mark_option")
    private long markOption;
    @Column(name = "marked_answer")
    private String markedAnswer;
    private String answer;
    private boolean result;

    public ResultTable(long id,String username,long courseId,long chapterId,String question,long markOption,String markedAnswer, String answer,boolean result ) {
        this.id = id;
        this.username = username;
        this.courseId = courseId;
        this.chapterId = chapterId;
        this.question = question;
        this.markOption = markOption;
        this.markedAnswer = markedAnswer;
        this.answer = answer;
        this.result = result;
    }

    public Results toResults() {
        return new Results(this.id,this.question,this.answer,this.markedAnswer,this.result);
    }

}
