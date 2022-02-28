package com.example.virtuallearn.Repository;

import com.example.virtuallearn.Repository.Table.ResultTable;
import com.example.virtuallearn.Repository.Table.TestTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ResultRepository extends JpaRepository<ResultTable,Long> {
    @Query(value = "select * from result_tbl where username = :username and course_id = :course_id and chapter_id = :chapter_id and question = :question", nativeQuery = true)
    public List<ResultTable> getTest(String username, long course_id, long chapter_id,String question );

    @Query(value = "select * from result_tbl where username = :username and course_id = :course_id and chapter_id = :chapter_id", nativeQuery = true)
    public List<ResultTable> getTests(String username, long course_id, long chapter_id );

    @Query(value = "select * from result_tbl where username = :username and course_id = :course_id and chapter_id = :chapter_id and result = :b", nativeQuery = true)
    List<ResultTable> getResult(String username, long course_id, long chapter_id, boolean b);
}

