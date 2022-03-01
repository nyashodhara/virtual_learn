package com.example.virtuallearn.Repository;

import com.example.virtuallearn.Repository.Table.InstructorTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<InstructorTable,Long> {
    InstructorTable getByInstructor(String instructor);

    @Query(value = "select * from instructor_tbl where id = :id", nativeQuery = true)
    List<InstructorTable> findByInstructorId(long id);
}
