package com.example.virtuallearn.Repository;

import com.example.virtuallearn.Repository.Table.PrivacyPolicyTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivacyPolicyRepository extends JpaRepository<PrivacyPolicyTable,Long> {

    @Query(value = "select * from privacy_policy_tbl where privacy_policy = :privacy_policy ", nativeQuery = true)
    public PrivacyPolicyTable getByPrivacyPolicy(String privacy_policy);
}