package com.example.virtuallearn.Controller.Response;

import lombok.Data;

@Data
public class ResponseWrapper <T>{
    private final ResultInfo result;
    private final T data;
}
