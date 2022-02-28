package com.example.virtuallearn.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Results {
    private final long id;
    private String question;
    private final String markedAnswer;
    private String answer;
    private boolean result;
}
