package com.example.waslatask1.PostDBEntityBuilders;

import com.example.waslatask1.Exceptions.CantParseNodeException;
import com.example.waslatask1.Exceptions.InaccessibleFileException;
import com.example.waslatask1.Models.Category;
import com.example.waslatask1.Models.PostDBEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AlAhramEntityBuilder implements PostDBEntityBuilder<Document> {
//    @Override
//    public Document getDocument(String path) {
//        try{
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(new URL(path).openStream());
//
//            return doc;
//        }
//        catch (Exception e)
//        {
//            throw new InaccessibleFileException(path);
//        }
//    }

    @Override
    public List<PostDBEntity> buildDBEntities(Document document, Category category) {
        String currentNode = "";
        try
        {
            List<PostDBEntity> dbPosts = new ArrayList<>();
            NodeList itemsNodes = document.getElementsByTagName("item");
            PostDBEntity post;
            for(int i=0; i<itemsNodes.getLength(); i++) // Loop through items in datasource
            {
                post = new PostDBEntity();
                Node itemNode = itemsNodes.item(i);
                if(itemNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element itemElement = (Element) itemNode;

                    currentNode = "title";
                    String title = parseTitleNode(itemElement);
                    currentNode = "description";
                    String desc = parseDescNode(itemElement);

                    post.setTitle_ar(title);
                    post.setBody_ar(desc);
                    post.setCategory(category);
                    post.setType(1);

                    dbPosts.add(post);
                }
            }
            return dbPosts;
        }
        catch (Exception e)
        {
            throw new CantParseNodeException(currentNode, e.getMessage());
        }
//        return null;
    }

    private String parseTitleNode(Element itemElement) {
        return itemElement.getElementsByTagName("title").item(0).getTextContent();
    }
    private String parseDescNode(Element itemElement) {
        return itemElement.getElementsByTagName("description").item(0).getTextContent();
    }
}
