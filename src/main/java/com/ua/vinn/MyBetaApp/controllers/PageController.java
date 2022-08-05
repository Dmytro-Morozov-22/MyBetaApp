package com.ua.vinn.MyBetaApp.controllers;

import com.ua.vinn.MyBetaApp.dto.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {

    @GetMapping("/home")
    public ResponseEntity<?> home(){
        return ResponseEntity.ok(new MessageResponse("It is home page! You are logged out"));
    }

    @GetMapping("/loginPage")
    public ResponseEntity<?> loginPage(){
        return ResponseEntity.ok(new MessageResponse("It is login page!"));
    }

}
