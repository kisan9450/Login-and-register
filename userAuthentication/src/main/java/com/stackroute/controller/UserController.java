package com.stackroute.controller;

import com.stackroute.domain.JwtResponse;
import com.stackroute.domain.User;
import com.stackroute.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class UserController {

    private UserRepository userRepository;
    @Value("${usercontroller.messages.exception1}")
    private String exceptionMessage1;

    @Value("${usercontroller.messages.exception2}")
    private String exceptionMessage2;

    @Value("${usercontroller.messages.exception3}")
    private String exceptionMessage3;

    @Value("${usercontroller.messages.exception4}")
    private String exceptionMessage4;

    @Value("${usercontroller.messages.exception5}")
    private String exceptionMessage5;

    @Autowired
    public UserController(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @GetMapping("getall")
    public ResponseEntity<?> getallUsers(){
        ResponseEntity responseEntity;
        try{
              List<User> users=userRepository.findAll();
            responseEntity= new ResponseEntity<List<User>>(users, HttpStatus.FOUND);
        }catch (Exception e){
            responseEntity= new ResponseEntity<String>("User Not Found",HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PostMapping("/saveuser")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        ResponseEntity responseEntity;
        try{
            userRepository.save(user);
            responseEntity= new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);

        }
        catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public JwtResponse login(@RequestBody User login) throws ServletException {
        JwtResponse jwtToken=new JwtResponse();
        if (login.getUserName() == null || login.getPassword() == null) {
            throw new ServletException("Please fill in username and password");
        }

        String username = login.getUserName();
        String password = login.getPassword();

        User user = userRepository.findByUserName(username);

        if (user == null) {
            throw new ServletException("Username not found.");
        }

        String pwd = user.getPassword();

        if (!password.equals(pwd)) {
            throw new ServletException("Invalid login. Please check your username and password.");
        }
               System.out.println("got request");
        String jwt;
        jwt = Jwts.builder().setSubject(username).claim("username", username).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        jwtToken.setToken(jwt);
        jwtToken.setUsername(username);
        System.out.println(jwtToken);
        return jwtToken;
    }
}
