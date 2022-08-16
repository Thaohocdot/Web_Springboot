package com.nghien.service;

import org.springframework.stereotype.Service;

import com.nghien.entities.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findAll() ;

    public Category findById(String id) ;

    public Category create(Category category) ;

    public Category update(Category category) ;

    public void delete(String id) ;
}
