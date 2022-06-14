package com.example.waslatask1.Fetchers;

import com.example.waslatask1.Exceptions.InaccessibleFileException;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class AlMasryAlYoumFetcher implements DataSourceFetcher<Document>{
    @Override
    public Document getDatasource(String path) {
        try{
            File xmlFile = new File(path);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            return doc;
        }
        catch (Exception e){
            throw new InaccessibleFileException(path);
        }
    }
}
