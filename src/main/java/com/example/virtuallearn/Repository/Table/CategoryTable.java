package com.example.virtuallearn.Repository.Table;

import com.example.virtuallearn.Entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "category_tbl")
@Data
@NoArgsConstructor
public class CategoryTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String category;

    public CategoryTable(long id, String category) {
        this.id = id;
        this.category = category;
    }

    public Category toCategory() {
        return new Category(this.id, this.category);
    }
}
