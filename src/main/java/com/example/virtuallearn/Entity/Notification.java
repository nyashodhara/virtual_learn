package com.example.virtuallearn.Entity;

import com.example.virtuallearn.Repository.Table.ChapterTable;
import com.example.virtuallearn.Repository.Table.NotificationTable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Notification {
    private long id;
    private String username;
    private String notification;

    public NotificationTable toNotificationTable() {
        return new NotificationTable(this.id,this.username, this.notification);
    }
}
