package com.example.democrud.controller;

import com.example.democrud.entity.User;
import com.example.democrud.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return userServiceImpl.saveUser(user);
    }
    @PostMapping("/addUsers")
    public List<User> addUsers(@RequestBody List<User> users){
        return userServiceImpl.saveUsers(users);
    }

    @GetMapping("/users")
    public List<User> findAllUser(){
        return userServiceImpl.getUsers();
    }
    @GetMapping("/users/{id}")
    public User findProductById(@PathVariable int id){
        return userServiceImpl.getUserById(id);
    }
    @GetMapping("/users-by-name/{name}")
    public List<User> findProductByName(@PathVariable String name){
        return userServiceImpl.getUserByName(name);
    }

    @PutMapping("/update-user")
    public User update(@RequestBody User user){
        return userServiceImpl.updateUser(user);
    }

    @DeleteMapping("/delete-user/{id}")
    public void deleteProduct(@PathVariable int id){
         userServiceImpl.deleteUser(id);
    }

    //_______________________________________________
    @GetMapping("/all-user-query")
    public List<User> query1(){
        return userServiceImpl.queryAllUser();
    }

    @PutMapping("/update-user-query")
    public void queryUpdate(@RequestBody User user){
        userServiceImpl.queryUpdateUser(user);
    }

    @GetMapping("/all-user-by-name-query/{name}")
    public List<User> query2(@PathVariable String name){
        return userServiceImpl.queryAllUserByName(name);
    }

    @GetMapping("/email-by-name-query/{name}")
    public List<Object> query3(@PathVariable String name){
        return userServiceImpl.queryFindEmailByName(name);
    }

    //________________________________assigmenet---------

    @GetMapping("/result/find-users-by-email")
    public List<User> query4(){
        return userServiceImpl.extractingDataFromApiMappingToUser();
    }

    @GetMapping("/result/find-phone-by-email")
    public List<Object> query5(){
        return userServiceImpl.extractingDataFromApiMappingToPhone();
    }




}
