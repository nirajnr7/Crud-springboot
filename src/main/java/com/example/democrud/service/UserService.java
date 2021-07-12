package com.example.democrud.service;


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
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Value("${service.url}")
    private String baseUrl;

    @Autowired
    public UserService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }


    public User saveUser(User user){
        return userRepository.save(user);
    }
    public List<User> saveUsers(List<User> users){
        return userRepository.saveAll(users);
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }
    public List<User> getUserByName(String name){
        return userRepository.findByName(name);
    }

    public String deleteUser(int id){
        userRepository.deleteById(id);
        return "User removed "+id;
    }

    public User updateUser(User user){
        User existingUser=userRepository.findById(user.getId()).orElse(null);
        if(existingUser!=null) {
            existingUser.setName(user.getName());
            existingUser.setAge(user.getAge());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            return userRepository.save(existingUser);
        }
        return new User();
    }

    //______________________________________________________________

    public List<User> queryAllUser(){
        return userRepository.queryAllUser();
    }
    public List<User> queryAllUserByName(String name){
        return userRepository.queryAllUserByName(name);
    }


    public List<Object> queryFindEmailByName(String name) {
        return userRepository.queryFindEmailByName(name);
    }

    public List<User> queryAllUserByEmail(List<String> emailList){
        return userRepository.queryAllUserByEmail(emailList);
    }
    public List<Object> querySelectiveByEmail(List<String> emailList){
        return userRepository.querySelectiveByEmail(emailList);
    }

    public List<User>  consumeAPI(){

        PageVO pageVO = restTemplate.getForObject(baseUrl, PageVO.class);
        if(pageVO!=null)
        {List<String> emailList = pageVO.getData().stream()
                .map(EmployeeVO::getEmail).collect(Collectors.toList());

        return queryAllUserByEmail(emailList);
        }
        return Collections.emptyList();

    }

    public List<Object>  consumeAPI2(){

        PageVO pageVO = restTemplate.getForObject(baseUrl, PageVO.class);
        if(pageVO!=null)
        {List<String> emailList = pageVO.getData().stream()
                .map(EmployeeVO::getEmail).collect(Collectors.toList());

            return querySelectiveByEmail(emailList);
        }
        return Collections.emptyList();

//        PageVO pageVO = restTemplate.getForObject(baseUrl, PageVO.class);
//        List<User> userList=userRepository.queryAllUser();
//        List<String> emailList = pageVO.getData().stream().map(x -> x.getEmail()).collect(Collectors.toList());
//        Set<String> set1= new HashSet<String>(emailList);
//
//        //
//        return  userList.stream().filter(x -> set1.contains(x.getEmail())).map(x->x.getPhone()).collect(Collectors.toList());


    }

}
