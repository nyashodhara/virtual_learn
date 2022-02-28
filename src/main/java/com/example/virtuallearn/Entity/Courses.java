package com.example.virtuallearn.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
public class Courses {
    @NotNull
    private final long id;
    @NotBlank
    private final String course;
    @NotBlank
    private final String category;
    @NotBlank
    private final String instructor;
}
