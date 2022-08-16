package com.nghien.service;

import org.springframework.stereotype.Service;

import com.nghien.entities.Account;

import java.util.List;


public interface AccountService {
    public List<Account> findAll() ;
    public Account findById(String username) ;
    public List<Account> getAdministrators() ;

}
