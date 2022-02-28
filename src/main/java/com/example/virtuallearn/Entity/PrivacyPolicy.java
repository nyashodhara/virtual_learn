package com.example.virtuallearn.Entity;

import com.example.virtuallearn.Repository.Table.PrivacyPolicyTable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrivacyPolicy {
    private final long id;
    private final String privacyPolicy;


    public PrivacyPolicyTable toPrivacyPolicyTable() {
        return new PrivacyPolicyTable(this.id, this.privacyPolicy);
    }
}


