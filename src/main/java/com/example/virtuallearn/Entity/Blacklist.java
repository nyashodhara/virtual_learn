package com.example.virtuallearn.Entity;

import com.example.virtuallearn.Repository.Table.BlacklistTable;
import com.example.virtuallearn.Repository.Table.CategoryTable;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class Blacklist {
    @NotNull
    private final long id;
    private String username;
    @NotBlank
    private final String token;

    public BlacklistTable toBlacklistTable() {
        return new BlacklistTable(this.id, this.username, this.token);
    }
}
