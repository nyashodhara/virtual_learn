package com.example.virtuallearn.Entity;

import com.example.virtuallearn.Repository.Table.HelpTable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Helps {
    private long id;
    private final long courseId;
    private final String question;
    private String answer;

}

