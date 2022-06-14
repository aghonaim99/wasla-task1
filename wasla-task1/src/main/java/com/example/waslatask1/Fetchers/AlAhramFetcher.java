package com.example.waslatask1.Fetchers;

import com.example.waslatask1.Exceptions.InaccessibleFileException;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;

public class AlAhramFetcher implements DataSourceFetcher<Document>{
    @Override
    public Document getDatasource(String path) {
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL(path).openStream());

            return doc;
        }
        catch (Exception e)
        {
            throw new InaccessibleFileException(path);
        }
    }
}
