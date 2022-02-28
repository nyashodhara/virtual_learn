package com.example.virtuallearn.Repository.Table;

import com.example.virtuallearn.Entity.PrivacyPolicy;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "privacy_policy_tbl")
@Data
@NoArgsConstructor
public class PrivacyPolicyTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "privacy_policy")
    private String privacyPolicy;

    public PrivacyPolicyTable(long id, String privacyPolicy) {
        this.id = id;
        this.privacyPolicy = privacyPolicy;
    }

    public PrivacyPolicy toPrivacyPolicy() {
        return new PrivacyPolicy(this.id, this.privacyPolicy);
    }
}
