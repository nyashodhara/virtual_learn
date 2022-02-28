package com.example.virtuallearn.Repository;

import com.example.virtuallearn.Repository.Table.ChapterContentTable;
import com.example.virtuallearn.Repository.Table.ChapterTable;
import com.example.virtuallearn.Repository.Table.TestTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<TestTable,Long> {
    @Query(value = "select * from test_tbl where question = :question and chapter_id = :chapter_id", nativeQuery = true)
    public TestTable getByQuestion(String question,long chapter_id);

    @Query(value = "select * from test_tbl where course_id = :course_id and chapter_id = :chapter_id", nativeQuery = true)
    public List<TestTable> getTest(long course_id, long chapter_id);

    @Query(value = "select * from test_tbl where course_id = :course_id and chapter_id = :chapter_id and id =:id", nativeQuery = true)
    List<TestTable> getTests(long course_id, long chapter_id, long id);

}