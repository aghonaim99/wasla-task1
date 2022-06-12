package com.example.waslatask1.Controllers;

import com.example.waslatask1.Exceptions.InvalidCategoryIDException;
import com.example.waslatask1.Models.Category;
import com.example.waslatask1.Models.PostDBEntity;
import com.example.waslatask1.PostDBEntityBuilders.AlMasryAlYoumEntityBuilder;
import com.example.waslatask1.Repositories.CategoryRepo;
import com.example.waslatask1.Repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class DataSourcesService {
    @Autowired
    PostRepo postRepo;
    @Autowired
    CategoryRepo categoryRepo;

    private Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try
        {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) );

            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Document accessXMLFile(String filename) throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(filename);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        return doc;
    }
    public String saveMasryYoumPosts()
    {
        try{
            Document doc = accessXMLFile("almasryalyoum.xml");
            AlMasryAlYoumEntityBuilder alMasryAlYoumEntityBuilder = new AlMasryAlYoumEntityBuilder();

            Category c = categoryRepo.findById(1L).orElseThrow(InvalidCategoryIDException::new);

            List<PostDBEntity> savedPosts = alMasryAlYoumEntityBuilder.buildEntity(doc,c);
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

    public String saveAhramPosts() throws ParserConfigurationException, IOException, SAXException {
//        String url = "https://www.ahram.org.eg/daily/archive/RssXml.aspx?CategoryID=25";
//        RestTemplate restTemplate = new RestTemplate();
//        String str =restTemplate.getForObject(url, String.class);

        String url = "https://www.ahram.org.eg/daily/archive/RssXml.aspx?CategoryID=25";
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL(url).openStream());
        return "str";

    }
}
