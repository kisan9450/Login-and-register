package com.stackroute.controller;


import com.stackroute.domain.Userprofiledomain;
import com.stackroute.domain.Userprofiledomain1;
import com.stackroute.service.UserprofileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserprofileController {


    @Autowired
    private KafkaTemplate <String, Userprofiledomain> kafkaTemplate;

    private static final String TOPIC = "user";


    private UserprofileService userprofileService;

    public UserprofileController(UserprofileService userprofileService){
        this.userprofileService = userprofileService;
    }

    @PostMapping("/userprofile")
    public ResponseEntity<?> idpost(@RequestBody Userprofiledomain1 userprofiledomain1){
        ResponseEntity responseEntity;
        userprofileService.saveid(userprofiledomain1);
        Userprofiledomain userprofiledomain = new Userprofiledomain();
        userprofiledomain.setPassword(userprofiledomain1.getPassword());
        userprofiledomain.setUserName(userprofiledomain1.getUserName());
        kafkaTemplate.send(TOPIC, userprofiledomain);
        responseEntity=new ResponseEntity<String>("Success created", HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/userprofile")
    public ResponseEntity<?> idget(){
        return new ResponseEntity<List<Userprofiledomain1>>(userprofileService.getallinfo(),HttpStatus.OK);
    }

}
