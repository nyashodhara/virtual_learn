package com.example.virtuallearn.Repository;

import com.example.virtuallearn.Repository.Table.TermsOfServicesTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsOfServicesRepository extends JpaRepository<TermsOfServicesTable,Long> {

    @Query(value = "select * from terms_of_services_tbl where terms_of_services = :terms_of_services ", nativeQuery = true)
    public TermsOfServicesTable getByTerms(String terms_of_services);
}


