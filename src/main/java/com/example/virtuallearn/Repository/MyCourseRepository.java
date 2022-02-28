package com.example.virtuallearn.Repository;

import com.example.virtuallearn.Repository.Table.CourseTable;
import com.example.virtuallearn.Repository.Table.MyCourseTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyCourseRepository extends JpaRepository<MyCourseTable,Long> {
    @Query(value = "select * from mycourse_tbl where username = :username", nativeQuery = true)
    List<MyCourseTable> getAllCourses(String username);

    @Query(value = "select * from mycourse_tbl where username = :username and id = :id", nativeQuery = true)
    List<MyCourseTable> getMyCourses(String username,long id);

    @Query(value = "select * from mycourse_tbl where username = :username and isongoing = :isongoing", nativeQuery = true)
    List<MyCourseTable> getOngoing(String username,boolean isongoing);
}
