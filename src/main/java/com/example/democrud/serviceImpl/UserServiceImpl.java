package com.example.democrud.serviceImpl;


import com.example.democrud.VO.EmployeeVO;
import com.example.democrud.VO.PageVO;
import com.example.democrud.entity.User;
import com.example.democrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements com.example.democrud.service.UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final String baseUrl;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate, @Value("${service.url}") String baseUrl) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> saveUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    //optional
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public void deleteUser(int id) {
        //OPTion change
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            userRepository.delete(existingUser);
        }
    }

    public User updateUser(User user) {
        //single query
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setAge(user.getAge());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            return userRepository.save(existingUser);
        }
        return new User();
    }

    //______________________________________________________________

    public void queryUpdateUser(User user){
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            userRepository.queryUpdateUser(user.getId(),user.getName(),user.getEmail(),user.getPhone(),user.getAge());
        }

    }

    public List<User> queryAllUser() {
        return userRepository.queryAllUser();
    }

    public List<User> queryAllUserByName(String name) {
        return userRepository.queryAllUserByName(name);
    }


    public List<Object> queryFindEmailByName(String name) {
        return userRepository.queryFindEmailByName(name);
    }

    public List<User> queryAllUserByEmail(List<String> emailList) {
        return userRepository.queryAllUserByEmail(emailList);
    }

    public List<Object> querySelectiveByEmail(List<String> emailList) {
        return userRepository.querySelectiveByEmail(emailList);
    }


    public List<User> extractingDataFromApiMappingToUser() {

        PageVO pageVO = restTemplate.getForObject(baseUrl, PageVO.class);

            List<String> emailList = pageVO.getData().stream()
                    .map(EmployeeVO::getEmail).collect(Collectors.toList());

            System.out.println(emailList);

            return queryAllUserByEmail(emailList);


    }

    public List<Object> extractingDataFromApiMappingToPhone() {

        PageVO pageVO = restTemplate.getForObject(baseUrl, PageVO.class);
        if (pageVO != null) {
            List<String> emailList = pageVO.getData().stream()
                    .map(EmployeeVO::getEmail).collect(Collectors.toList());

            return querySelectiveByEmail(emailList);
        }
        return Collections.emptyList();


    }

}
