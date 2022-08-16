package com.nghien.service.impl;

import com.nghien.entities.Account;
import com.nghien.entities.Authority;
import com.nghien.repository.AccountRepository;
import com.nghien.repository.AuthorityRepository;
import com.nghien.service.AuthorityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }
    @Override
    public Authority create(Authority auth) {
        return authorityRepository.save(auth);
    }
    @Override
    public void delete(Integer id) {
        authorityRepository.deleteById(id);
    }
    @Override
    public List<Authority> findAuthoritiesOfAdministrators() {
        List<Account> accounts = accountRepository.getAdministrators();
        return authorityRepository.authoritiesOf(accounts);
    }
}
