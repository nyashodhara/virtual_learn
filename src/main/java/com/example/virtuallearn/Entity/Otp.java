package com.example.virtuallearn.Entity;

import com.example.virtuallearn.Repository.Table.OtpTable;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class Otp {
    @NotNull
    private final long id;
    @NotNull
    private final long phoneNumber;
    @NotNull
    private final long otp;

    public OtpTable toOtpTable() {
        return new OtpTable(this.id,this.phoneNumber,this.otp);
    }
}
