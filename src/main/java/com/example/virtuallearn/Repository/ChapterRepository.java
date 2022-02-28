package com.example.virtuallearn.Repository;

import com.example.virtuallearn.Repository.Table.ChapterTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<ChapterTable, Long> {
    @Query(value = "select * from chapter_tbl where course_id =:course_id and chapter = :chapter", nativeQuery = true)
    public ChapterTable getByChapter(long course_id,String chapter);

    @Query(value = "select * from chapter_tbl where course_id = :course_id and id = :id", nativeQuery = true)
    public List<ChapterTable> getByCourseAndChapter(long course_id, long id);

    @Query(value = "select * from chapter_tbl where id = :id", nativeQuery = true)
    ChapterTable getChapter(long id);
}

