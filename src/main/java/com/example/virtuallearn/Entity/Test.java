package com.example.virtuallearn.Entity;

import com.example.virtuallearn.Repository.Table.TestTable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Test {
    private final long id;
    private final long chapterId;
    private long courseId;
    private final String question;
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String answer;

    public TestTable toTestTable() {
        return new TestTable(this.id,this.chapterId,this.courseId, this.question,this.a,this.b,this.c,this.d,this.answer);
    }
}
