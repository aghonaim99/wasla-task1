package com.example.waslatask1.Services;

import com.example.waslatask1.Models.Category;
import com.example.waslatask1.Repositories.CategoryRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class CategoryServiceTests {
    @InjectMocks
    CategoryService categoryService;

    @Mock CategoryRepo categoryRepo;

    private List<Category> initCategories(){
        Category expectedCategory = new Category();
        expectedCategory.setId(1l);
        expectedCategory.setTitle_en("title1");
        expectedCategory.setTitle_ar("عنوان");

        List<Category> expected = new ArrayList<Category>();
        expected.add(expectedCategory);

        return expected;
    }

    @Test
    public void getFirstCateg(){
        List<Category> expected = initCategories();

        PageRequest p = PageRequest.of(0, 1, Sort.by("id"));
        Page<Category> mockPage = new PageImpl<Category>(expected);

        when(categoryRepo.findAll(p)).thenReturn(mockPage);

        List<Category> actual = new ArrayList<Category>();

        actual = categoryService.getCategoriesPageable(0,1);



        Assert.assertTrue(expected.equals(actual));


    }
}
