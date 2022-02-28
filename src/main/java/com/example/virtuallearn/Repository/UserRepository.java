package com.example.virtuallearn.Repository;

import com.example.virtuallearn.Entity.User;
import com.example.virtuallearn.Repository.Table.ResultTable;
import com.example.virtuallearn.Repository.Table.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserTable,Long> {

    @Query(value = "select * from user_tbl where phone_number = :phone_number", nativeQuery = true)
    UserTable getUserByPhoneNumber(Long phone_number);

    UserTable getByUsername(String username);

    User findByUsername(String username);

    @Query(value = "select * from user_tbl where username = :username and role = :role", nativeQuery = true)
    UserTable getAdmin(String username, String role);

}
