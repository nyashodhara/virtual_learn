package com.example.virtuallearn.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class Overview {
    @NotBlank
    private final String course;
    @NotBlank
    private final String overview;
}
