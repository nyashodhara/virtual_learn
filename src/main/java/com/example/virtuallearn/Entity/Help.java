package com.example.virtuallearn.Entity;

import com.example.virtuallearn.Repository.Table.HelpTable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Help {
    private long id;
    private String username;
    private final long courseId;
    private final String question;
    private String answer;


    public HelpTable toHelpTable() {
        return new HelpTable(this.username,this.courseId,this.question,this.answer);
    }
}

