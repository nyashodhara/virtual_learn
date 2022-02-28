package com.example.virtuallearn.Repository;

import com.example.virtuallearn.Repository.Table.BlacklistTable;
import com.example.virtuallearn.Repository.Table.CategoryTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlacklistRepository extends JpaRepository<BlacklistTable,Long> {
    @Query(value = "select * from blacklist_tbl where token = :token", nativeQuery = true)
    BlacklistTable getByUsername(String token);
}
