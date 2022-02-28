package com.example.virtuallearn.Entity;

import com.example.virtuallearn.Repository.Table.TermsOfServicesTable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TermsOfServices {
    private final long id;
    private final String termsOfServices;

    public TermsOfServicesTable toTermsOfServicesTable() {
        return new TermsOfServicesTable(this.id,this.termsOfServices);
    }
}

