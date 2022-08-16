package com.nghien.service;

import java.util.List;

import com.nghien.entities.Authority;

public interface AuthorityService {
    public List<Authority> findAll() ;

    public Authority create(Authority auth) ;

    public void delete(Integer id) ;

    public List<Authority> findAuthoritiesOfAdministrators() ;
}
