package com.nghien.api;

import com.nghien.entities.Category;
import com.nghien.repository.CategoryRepository;
import com.nghien.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public List<Category>getAll(){
        return categoryService.findAll();
    }
}
