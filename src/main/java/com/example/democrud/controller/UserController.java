package com.example.democrud.controller;

import com.example.democrud.entity.User;
import com.example.democrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return userService.saveUser(user);
    }
    @PostMapping("/addUsers")
    public List<User> addUsers(@RequestBody List<User> users){
        return userService.saveUsers(users);
    }

    @GetMapping("/users")
    public List<User> findAllUser(){
        return userService.getUsers();
    }
    @GetMapping("/user/{id}")
    public User findProductById(@PathVariable int id){
        return userService.getUserById(id);
    }
    @GetMapping("/userByName/{name}")
    public List<User> findProductByName(@PathVariable String name){
        return userService.getUserByName(name);
    }

    @PutMapping("/updateUser")
    public User update(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        return userService.deleteUser(id);
    }

    //_______________________________________________
    @GetMapping("/queryGetAllUser")
    public List<User> query1(){
        return userService.queryAllUser();
    }

    @GetMapping("/queryAllUserByName/{name}")
    public List<User> query2(@PathVariable String name){
        return userService.queryAllUserByName(name);
    }

    @GetMapping("/queryFindEmailByName/{name}")
    public List<Object> query3(@PathVariable String name){
        return userService.queryFindEmailByName(name);
    }

    //________________________________assigmenet---------

    @GetMapping("/queryFindUserListByEmail")
    public List<User> query4(){
        return userService.consumeAPI();
    }

    @GetMapping("/finalOutput")
    public List<Object> query5(){
        return userService.consumeAPI2();
    }




}
