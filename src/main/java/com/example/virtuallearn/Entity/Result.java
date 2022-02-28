package com.example.virtuallearn.Entity;

import com.example.virtuallearn.Repository.Table.ResultTable;
import com.example.virtuallearn.Repository.Table.TestTable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private final long id;
    private String username;
    private long courseId;
    private long chapterId;
    private String question;
    private final long markOption;
    private String markedAnswer;
    private String answer;
    private boolean result;

    public ResultTable toResultTable() {
        return new ResultTable(this.id,this.username,this.courseId,this.chapterId,this.question,this.markOption,this.markedAnswer,this.answer,this.result);
    }
}
