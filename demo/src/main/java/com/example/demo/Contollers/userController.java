package com.example.demo.Contollers;

import com.example.demo.Entities.UserEntity;
import com.example.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class userController {
    private final UserService userService;
    @GetMapping("/hello/{name}")
    public ResponseEntity<Object> helloWorld(@PathVariable String name){
        String returnValue = "Congratulations, "+name+"!.\nYou've successfully built your first API";
        ResponseEntity<Object> response = new ResponseEntity<>(returnValue, HttpStatus.OK);
        return response;
    }
    @PostMapping("/users/add")
    public ResponseEntity<Object>createUser(@RequestBody UserEntity userEntity){
        return userService.createUser(userEntity);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable long id){

        return userService.findById(id);
    }
    @PutMapping("/users/update")
    public ResponseEntity<Object> updateUser(@RequestBody UserEntity userEntity){

        return userService.updateUser(userEntity);
    }
    @DeleteMapping("/users/delete/")
    public ResponseEntity<Object> deleteUser(@RequestBody UserEntity userEntity){

        return userService.deleteUser(userEntity);
    }
}