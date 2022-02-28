package com.example.virtuallearn.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class Tests {
    private final long id;
    private final String question;
    private final String a;
    private final String b;
    private final String c;
    private final String d;
}
