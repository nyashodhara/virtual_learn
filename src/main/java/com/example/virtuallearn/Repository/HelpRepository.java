package com.example.virtuallearn.Repository;

import com.example.virtuallearn.Entity.Help;
import com.example.virtuallearn.Repository.Table.HelpTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpRepository extends JpaRepository<HelpTable,Long> {

    List<HelpTable> getByUsername(String username);

    @Query(value = "select * from help_tbl where id = :id", nativeQuery = true)
    List<HelpTable> getByQuestionId(long id);
}
