package com.example.virtuallearn.Repository.Table;

import com.example.virtuallearn.Entity.TermsOfServices;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "terms_of_services_tbl")
@Data
@NoArgsConstructor
public class TermsOfServicesTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "terms_of_services")
    private String termsOfServices;

    public TermsOfServicesTable(long id, String termsOfServices) {
        this.id = id;
        this.termsOfServices = termsOfServices;
    }

    public TermsOfServices toTermsOfServices() {
        return new TermsOfServices(this.id, this.termsOfServices);
    }
}

