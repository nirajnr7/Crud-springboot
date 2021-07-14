package com.example.democrud.repository;

import com.example.democrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "update user u set u.name = :name,u.email =:email,u.phone=:phone,u.age=:age where u.id=:id",nativeQuery = true)
    void queryUpdateUser(@Param("id") int id,@Param("name") String name ,@Param("email") String email,@Param("phone") String phone,@Param("age") int age);

    @Query(value = "select  * from user",nativeQuery = true)
     List<User> queryAllUser();

    @Query(value = "select * from user where name =:name",nativeQuery = true)
     List<User> queryAllUserByName(@Param("name") String name);

    @Query(value = "select id,email from user where name =:name",nativeQuery = true)
    List<Object> queryFindEmailByName(@Param("name") String name);
    //_______________________________________________________________

    @Query(value = "select * from user where email in :email",nativeQuery = true)
    List<User> queryAllUserByEmail(@Param("email") List<String> email);

    @Query(value = "select phone from user where email in :email",nativeQuery = true)
    List<Object> querySelectiveByEmail(@Param("email") List<String> email);
}
