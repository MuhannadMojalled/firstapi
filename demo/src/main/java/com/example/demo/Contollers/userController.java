package com.example.demo.Contollers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class userController {
    @GetMapping("/hey/{x}")
    public ResponseEntity<Object> firstApi(@PathVariable String x){
        return new ResponseEntity<>("hello "+x, HttpStatus.OK);
    }
}
