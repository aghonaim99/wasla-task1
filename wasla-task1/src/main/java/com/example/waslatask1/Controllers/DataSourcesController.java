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

    @GetMapping("/masryyoumrss")
    public ResponseEntity<String> saveMasryYoumPosts() {
        String postsStatus = dsService.saveMasryYoumPosts();

        return new ResponseEntity<>(postsStatus, new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/ahramrss")
    public ResponseEntity<String> saveAhramPosts() throws ParserConfigurationException, IOException, SAXException {
        String postsStatus = dsService.saveAhramPosts();

        return new ResponseEntity<>(postsStatus, new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/getposts")
    public ResponseEntity<String> getPostsFromDataSources(){
        String MasryYoumStatus, Youm7Status;

        MasryYoumStatus = dsService.saveMasryYoumPosts();
        Youm7Status = dsService.saveAhramPosts();

        String status = MasryYoumStatus == "Posts saved successfully" &&
                        Youm7Status == "Posts saved successfully"
                        ?
                        "Success" : "One (or more) failed";

        return new ResponseEntity<>(status, new HttpHeaders(), HttpStatus.CREATED);
    }

}
