package com.example.virtuallearn.Repository;

import com.example.virtuallearn.Repository.Table.InstructorTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<InstructorTable,Long> {
    @Query(value = "select * from instructor_tbl where course_id = :course_id", nativeQuery = true)

    public InstructorTable getByCourseId(long course_id);

    List<InstructorTable> findByCourseId(long courseId);
}
