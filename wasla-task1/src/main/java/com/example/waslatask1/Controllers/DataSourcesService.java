package com.example.waslatask1.Controllers;

import com.example.waslatask1.Exceptions.InvalidCategoryIDException;
import com.example.waslatask1.Models.Category;
import com.example.waslatask1.Models.PostDBEntity;
import com.example.waslatask1.PostDBEntityBuilders.AlAhramEntityBuilder;
import com.example.waslatask1.PostDBEntityBuilders.AlMasryAlYoumEntityBuilder;
import com.example.waslatask1.Repositories.CategoryRepo;
import com.example.waslatask1.Repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.List;
import java.util.Optional;

@Service
public class DataSourcesService {
    @Autowired
    PostRepo postRepo;
    @Autowired
    CategoryRepo categoryRepo;


    public String saveMasryYoumPosts()
    {
        try{
            AlMasryAlYoumEntityBuilder alMasryAlYoumEntityBuilder = new AlMasryAlYoumEntityBuilder();

            Document doc = alMasryAlYoumEntityBuilder.getDocument("almasryalyoum.xml");
//            Category c = categoryRepo.findById(1L).orElseThrow(InvalidCategoryIDException::new);
            Optional<Category> c = categoryRepo.findById(1L);

            List<PostDBEntity> savedPosts = alMasryAlYoumEntityBuilder.buildDBEntities(doc,c.get());
            postRepo.saveAll(savedPosts);

            return "Posts saved successfully";
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
//        return null;
    }

    public String saveAhramPosts() {

        AlAhramEntityBuilder alAhramEntityBuilder = new AlAhramEntityBuilder();

        Document doc = alAhramEntityBuilder.getDocument("https://www.ahram.org.eg/daily/archive/RssXml.aspx?CategoryID=25");
        Category c = categoryRepo.findById(2L).orElseThrow(InvalidCategoryIDException::new);

        List<PostDBEntity> savedPosts = alAhramEntityBuilder.buildDBEntities(doc,c);
        postRepo.saveAll(savedPosts);

        return "Posts saved successfully";
    }
}