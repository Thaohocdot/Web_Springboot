package com.nghien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nghien.entities.Account;
import com.nghien.entities.Authority;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Integer> {

    @Query("SELECT DISTINCT a FROM Authority a WHERE a.account IN ?1")
    List<Authority> authoritiesOf(List<Account> accounts);
}
