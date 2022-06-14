package com.example.waslatask1.Services;

import com.example.waslatask1.Models.Category;
import com.example.waslatask1.Models.PostDBEntity;
import com.example.waslatask1.PostDBEntityBuilders.AlAhramEntityBuilder;
import com.example.waslatask1.PostDBEntityBuilders.AlMasryAlYoumEntityBuilder;
import com.example.waslatask1.Repositories.CategoryRepo;
import com.example.waslatask1.Repositories.PostRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.lenient;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DataSourcesServiceTests {
    @InjectMocks
    DataSourcesService dsService = new DataSourcesService();
    @Mock
    PostRepo postRepo;
    @Mock
    CategoryRepo categoryRepo;


    private Category initFirstCategory(){
        Category c = new Category();
        c.setId(1L);
        c.setTitle_en("title1");
        c.setTitle_ar("عنوان");

        return c;
    }

    @Test
    public void saveMasryYoumPostsSucceeds(){
        AlMasryAlYoumEntityBuilder alMasryAlYoumEntityBuilder = new AlMasryAlYoumEntityBuilder();
        Document doc = alMasryAlYoumEntityBuilder.getDocument("almasryalyoum.xml");

        List<PostDBEntity> expectedPosts = alMasryAlYoumEntityBuilder.buildDBEntities(doc,initFirstCategory());
        Optional<Category> c = Optional.of(initFirstCategory());

        lenient().when(categoryRepo.findById(1L)).thenReturn(c);
        lenient().when(postRepo.saveAll(expectedPosts)).thenReturn(expectedPosts);

        String expected = "Posts saved successfully";
        String actual = dsService.saveMasryYoumPosts();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void saveAhramPostsSucceeds() throws ParserConfigurationException, IOException, SAXException {
        AlAhramEntityBuilder alAhramEntityBuilder = new AlAhramEntityBuilder();

        // test may fail in the future if real rss changed data
        File xmlFile = new File("ahram.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        List<PostDBEntity> expectedPosts = alAhramEntityBuilder.buildDBEntities(doc,initFirstCategory());
        Optional<Category> c = Optional.of(initFirstCategory());

        lenient().when(categoryRepo.findById(2L)).thenReturn(c);
        lenient().when(postRepo.saveAll(expectedPosts)).thenReturn(expectedPosts);

        String expected = "Posts saved successfully";
        String actual = dsService.saveAhramPosts();

        Assert.assertEquals(expected, actual);
    }
}
