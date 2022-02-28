package com.example.virtuallearn.Repository;

import com.example.virtuallearn.Repository.Table.CourseTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseTable,Long> {
    @Query(value = "select * from course_tbl where course = :course", nativeQuery = true)
    CourseTable getByCourse(String course);

    @Query(value = "select * from course_tbl where id = :id", nativeQuery = true)
    List <CourseTable> getAllCourses(long id);

    @Query(value = "select * from course_tbl where category_id = :category_id", nativeQuery = true)
    List <CourseTable> getCourseByCategory(long category_id);

    @Query(value = "select * from course_tbl ", nativeQuery = true)
    public List<CourseTable> getall();

    @Query(value = "select * from course_tbl where id = :id", nativeQuery = true)
    List <CourseTable> getOverviewByCourse(long id);

    @Query(value = "select * from course_tbl where id = :id", nativeQuery = true)
    List<CourseTable> findByCourseId(long id);
}
