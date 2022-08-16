package com.nghien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nghien.entities.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {

    @Query("SELECT DISTINCT ar.account  FROM Authority ar WHERE ar.role.id IN ('DIRE', 'STAF')")
    List<Account> getAdministrators();
}
