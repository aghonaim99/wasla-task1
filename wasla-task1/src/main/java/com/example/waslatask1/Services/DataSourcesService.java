package com.example.waslatask1.Services;

import com.example.waslatask1.Exceptions.InvalidCategoryIDException;
import com.example.waslatask1.Fetchers.AlAhramFetcher;
import com.example.waslatask1.Fetchers.AlMasryAlYoumFetcher;
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
        AlMasryAlYoumFetcher fetcher = new AlMasryAlYoumFetcher();
        try{
            AlMasryAlYoumEntityBuilder alMasryAlYoumEntityBuilder = new AlMasryAlYoumEntityBuilder();

//            Document doc = alMasryAlYoumEntityBuilder.getDocument("almasryalyoum.xml");
            Document doc = fetcher.getDatasource("almasryalyoum.xml");
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
        AlAhramFetcher fetcher = new AlAhramFetcher();
//        Document doc = alAhramEntityBuilder.getDocument("https://www.ahram.org.eg/daily/archive/RssXml.aspx?CategoryID=25");
        Document doc = fetcher.getDatasource("https://www.ahram.org.eg/daily/archive/RssXml.aspx?CategoryID=25");
        Category c = categoryRepo.findById(2L).orElseThrow(InvalidCategoryIDException::new);

        List<PostDBEntity> savedPosts = alAhramEntityBuilder.buildDBEntities(doc,c);
        postRepo.saveAll(savedPosts);

        return "Posts saved successfully";
    }
}
