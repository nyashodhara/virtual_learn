package com.example.virtuallearn.Repository;

import com.example.virtuallearn.Repository.Table.BlacklistTable;
import com.example.virtuallearn.Repository.Table.NotificationTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationTable,Long> {
    @Query(value = "select * from notification_tbl where username = :username or username = :user", nativeQuery = true)
    List<NotificationTable> getNotificationByUsername(String username,String user);
}
