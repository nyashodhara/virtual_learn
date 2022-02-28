package com.example.virtuallearn.Exception;

import com.example.virtuallearn.Controller.Response.ResultInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EnterValidCredentialException extends RuntimeException{
    private final ResultInfo result;
}
