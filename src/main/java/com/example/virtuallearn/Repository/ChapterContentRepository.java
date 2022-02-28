package com.example.virtuallearn.Repository;

import com.example.virtuallearn.Repository.Table.ChapterContentTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface ChapterContentRepository extends JpaRepository<ChapterContentTable,Long> {

    @Query(value = "select * from chapter_content_tbl where content = :content and link = :link", nativeQuery = true)
    public ChapterContentTable getByCourseAndChapter(String content, String link);


    List<ChapterContentTable> findByCourseId(long courseId);
}
