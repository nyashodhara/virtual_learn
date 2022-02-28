package com.example.virtuallearn.Repository;

import com.example.virtuallearn.Repository.Table.OtpTable;
import com.example.virtuallearn.Repository.Table.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<OtpTable,Long> {

    @Query(value = "select * from otp_tbl where phone_number = :phone_number", nativeQuery = true)
    public OtpTable getUserByPhoneNumber(Long phone_number);
}

