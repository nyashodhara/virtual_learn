package com.example.virtuallearn.Entity;

import com.example.virtuallearn.Repository.Table.CategoryTable;
import com.example.virtuallearn.Repository.Table.OtpTable;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class Category {
    @NotNull
    private final long id;
    @NotBlank
    private final String category;

    public CategoryTable toCategoryTable() {
        return new CategoryTable(this.id,this.category);
    }
}

