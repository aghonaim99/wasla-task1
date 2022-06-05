package com.example.waslatask1.Controllers;

import com.example.waslatask1.Models.Category;
import com.example.waslatask1.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String hi()
    {
        return "hi";
    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<List<Category>> getAllCategories()
    {
        List<Category> categs = categoryService.getAllCategories();

        return new ResponseEntity<List<Category>>(categs, new HttpHeaders(), HttpStatus.OK);
    }
}
