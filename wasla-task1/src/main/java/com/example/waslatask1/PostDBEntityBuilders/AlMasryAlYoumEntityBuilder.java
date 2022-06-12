package com.example.waslatask1.PostDBEntityBuilders;

import com.example.waslatask1.Models.Category;
import com.example.waslatask1.Models.PostDBEntity;
import com.example.waslatask1.Repositories.CategoryRepo;
import com.example.waslatask1.Repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class AlMasryAlYoumEntityBuilder implements PostDBEntityBuilder<Document>{
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    PostRepo postRepo;

    @Override
    public List<PostDBEntity> buildEntity(Document doc, Category category) {
        try
        {
            List<PostDBEntity> dbPosts = new ArrayList<>();
            NodeList itemsNodes = doc.getElementsByTagName("item");
            PostDBEntity post;
            for(int i=0; i<itemsNodes.getLength(); i++)
            {
                post = new PostDBEntity();
                Node itemNode = itemsNodes.item(i);
                if(itemNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element itemElement = (Element) itemNode;

                    String title = itemElement.getElementsByTagName("title").item(0).getTextContent();
                    String desc = itemElement.getElementsByTagName("description").item(0).getTextContent().split(">")[2];
                    String imgurl = itemElement.getElementsByTagName("description").item(0).getTextContent().split("src='")[1].split("'")[0];

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
            System.out.println(e.getMessage());
        }
        return null;
    }
}
