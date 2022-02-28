package com.example.virtuallearn.Repository.Table;

import com.example.virtuallearn.Entity.MyCourses;
import com.example.virtuallearn.Entity.Notification;
import com.example.virtuallearn.Entity.Notifications;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "notification_tbl")
@Data
@NoArgsConstructor
public class NotificationTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String notification;

    public NotificationTable(long id,String username, String notification) {
        this.id = id;
        this.username = username;
        this.notification = notification;
    }

    public Notifications toNotifications() {
        return new Notifications(this.notification);
    }
}
