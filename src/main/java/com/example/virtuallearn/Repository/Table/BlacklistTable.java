package com.example.virtuallearn.Repository.Table;

import com.example.virtuallearn.Entity.Blacklist;
import com.example.virtuallearn.Entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "blacklist_tbl")
@Data
@NoArgsConstructor
public class BlacklistTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String token;

    public BlacklistTable(long id, String username, String token) {
        this.id = id;
        this.username = username;
        this.token = token;
    }

    public Blacklist toBlacklist() {
        return new Blacklist(this.id, this.username, this.token);
    }
}
