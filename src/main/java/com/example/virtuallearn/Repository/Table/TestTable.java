package com.example.virtuallearn.Repository.Table;

import com.example.virtuallearn.Entity.ChapterContent;
import com.example.virtuallearn.Entity.Test;
import com.example.virtuallearn.Entity.Tests;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "test_tbl")
@Data
@NoArgsConstructor
public class TestTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    @Column(name = "chapter_id")
    private  long chapterId;
    @Column(name = "course_id")
    private  long courseId;
    private  String question;
    private  String a;
    private  String b;
    private  String c;
    private  String d;
    private  String answer;

    public TestTable(long id,long chapterId,long courseId,String question,String a,String b,String c,String d, String answer ) {
        this.id = id;
        this.chapterId = chapterId;
        this.courseId = courseId;
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
    }

    public Test toTest() {
        return new Test(this.id,this.chapterId,this.courseId, this.question,this.a,this.b,this.c,this.d,this.answer);
    }

    public Tests toTests() {
        return new Tests(this.id,this.question,this.a,this.b,this.c,this.d);
    }

}
