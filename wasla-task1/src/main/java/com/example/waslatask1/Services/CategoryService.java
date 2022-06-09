package com.example.waslatask1.Services;

import com.example.waslatask1.Models.Category;
import com.example.waslatask1.Repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    public List<Category> getAllCategories(){

        return categoryRepo.findAll();
    }

    public List<Category> getCategoriesPageable(int pageNum, int rowsPerPage)
    {
        PageRequest p = PageRequest.of(pageNum, rowsPerPage, Sort.by("id"));
        Page<Category> retrieved = categoryRepo.findAll(p);

        return retrieved.getContent();
    }
}
