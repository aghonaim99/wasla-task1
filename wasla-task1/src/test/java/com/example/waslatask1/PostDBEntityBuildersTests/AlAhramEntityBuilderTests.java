package com.example.waslatask1.PostDBEntityBuildersTests;

import com.example.waslatask1.Exceptions.InaccessibleFileException;
import com.example.waslatask1.Models.Category;
import com.example.waslatask1.PostDBEntityBuilders.AlAhramEntityBuilder;
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
import java.net.MalformedURLException;
import java.net.URL;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AlAhramEntityBuilderTests {

    private Category initFirstCategory(){
        Category c = new Category();
        c.setId(1L);
        c.setTitle_en("title1");
        c.setTitle_ar("عنوان");

        return c;
    }

//    @Test
//    public void getDocumentShouldReturnDocumentSuccessfully() throws ParserConfigurationException, IOException, SAXException {
//        org.w3c.dom.Document expected;
//        File xmlFile = new File("ahram.xml");
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        expected = builder.parse(xmlFile);
//
//        AlAhramEntityBuilder alAhramEntityBuilder = new AlAhramEntityBuilder();
//
//        org.w3c.dom.Document actual = alAhramEntityBuilder.getDocument("https://www.ahram.org.eg/daily/archive/RssXml.aspx?CategoryID=25");
//
//        Assert.assertEquals(expected, actual);
//    }

    @Test
    public void buildEntities() throws ParserConfigurationException, IOException, SAXException {
        Category c = initFirstCategory();
        org.w3c.dom.Document doc;
        File xmlFile = new File("ahram.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.parse(xmlFile);

        AlAhramEntityBuilder alAhramEntityBuilder = new AlAhramEntityBuilder();



    }
}
