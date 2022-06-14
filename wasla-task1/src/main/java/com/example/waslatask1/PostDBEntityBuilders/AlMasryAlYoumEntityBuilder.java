package com.example.waslatask1.PostDBEntityBuilders;

import com.example.waslatask1.Exceptions.CantParseNodeException;
import com.example.waslatask1.Exceptions.InaccessibleFileException;
import com.example.waslatask1.Models.Category;
import com.example.waslatask1.Models.PostDBEntity;
import com.example.waslatask1.Repositories.CategoryRepo;
import com.example.waslatask1.Repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AlMasryAlYoumEntityBuilder implements PostDBEntityBuilder<Document>{
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    PostRepo postRepo;

//    @Override
//    public Document getDocument(String path) {
//        try{
//            File xmlFile = new File(path);
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document doc = builder.parse(xmlFile);
//
//            return doc;
//        }
//        catch (Exception e){
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
            for(int i=0; i<itemsNodes.getLength(); i++)
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
                    currentNode = "img";
                    String imgurl = extractImgURL(itemElement);

                    post.setTitle_ar(title);
                    post.setBody_ar(desc);
                    post.setCategory(category);
                    post.setType(1);
                    post.setImg_url(imgurl);

                    dbPosts.add(post);
                }
            }
            return dbPosts;
        }
        catch (Exception e)
        {
            throw new CantParseNodeException(currentNode, e.getMessage());
        }
    }

    private String parseTitleNode(Element itemElement) {
        return itemElement.getElementsByTagName("title").item(0).getTextContent();
    }
    private String parseDescNode(Element itemElement) {
        return itemElement.getElementsByTagName("description").item(0).getTextContent().split(">")[2];
    }
    private String extractImgURL(Element itemElement) {
        return itemElement.getElementsByTagName("description").item(0).getTextContent().split("src='")[1].split("'")[0];
    }
}
