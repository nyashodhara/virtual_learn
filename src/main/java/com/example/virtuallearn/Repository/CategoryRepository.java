package com.example.virtuallearn.Repository;

import com.example.virtuallearn.Entity.Category;
import com.example.virtuallearn.Repository.Table.CategoryTable;
import com.example.virtuallearn.Repository.Table.CourseTable;
import com.example.virtuallearn.Repository.Table.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryTable,Long> {
    @Query(value = "select * from category_tbl where category = :category", nativeQuery = true)
    public CategoryTable getByCategory(String category);

    Category findByCategory(String savedCategory);

    @Query(value = "select * from category_tbl ", nativeQuery = true)
    public List<CategoryTable> getall();

    @Query(value = "select * from category_tbl where id = :id", nativeQuery = true)
    public List<CategoryTable> findByCategoryId(long id);
}
