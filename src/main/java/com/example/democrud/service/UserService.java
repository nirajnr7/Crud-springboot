package com.example.democrud.service;

import com.example.democrud.entity.User;

import java.util.List;


public interface UserService {

    User saveUser(User user);

    List<User> saveUsers(List<User> users);

    List<User> getUsers();

    User getUserById(int id);

    List<User> getUserByName(String name);

    void deleteUser(int id);

    User updateUser(User user);

    void queryUpdateUser(User user);

    List<User> queryAllUser();

    List<User> queryAllUserByName(String name);

    List<Object> queryFindEmailByName(String name);

    List<User> queryAllUserByEmail(List<String> emailList);

    List<Object> querySelectiveByEmail(List<String> emailList);

    List<User> extractingDataFromApiMappingToUser();

    List<Object> extractingDataFromApiMappingToPhone();

}
