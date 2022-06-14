package com.example.waslatask1.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
@RequestMapping("/ds")
public class DataSourcesController {
    @Autowired
    DataSourcesService dsService;

//    @GetMapping("/masryyoumrss")
//    public ResponseEntity<String> saveMasryYoumPosts() {
//        String postsStatus = dsService.saveMasryYoumPosts();
//
//        return new ResponseEntity<>(postsStatus, new HttpHeaders(), HttpStatus.CREATED);
//    }

//    @GetMapping("/ahramrss")
//    public ResponseEntity<String> saveAhramPosts() throws ParserConfigurationException, IOException, SAXException {
//        String postsStatus = dsService.saveAhramPosts();
//
//        return new ResponseEntity<>(postsStatus, new HttpHeaders(), HttpStatus.CREATED);
//    }

    @GetMapping("/getposts")
    public ResponseEntity<String> getPostsFromDataSources(){
        String MasryYoumStatus, Youm7Status;

        MasryYoumStatus = dsService.saveMasryYoumPosts();
        Youm7Status = dsService.saveAhramPosts();

        HttpStatus httpStatus;

        String status;
        if(MasryYoumStatus == "Posts saved successfully" && Youm7Status == "Posts saved successfully")
        {
            status = "Success";
            httpStatus = HttpStatus.CREATED;
        }
        else if(MasryYoumStatus == "Posts saved successfully" || Youm7Status == "Posts saved successfully")
        {
            status = "One (or more) failed";
            httpStatus = HttpStatus.OK;
        }
        else
        {
            status = "Failed";
            httpStatus = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<>(status,
                new HttpHeaders(),
                httpStatus);
    }

}
