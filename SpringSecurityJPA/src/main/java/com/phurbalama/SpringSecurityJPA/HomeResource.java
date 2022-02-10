package com.phurbalama.SpringSecurityJPA;

import com.phurbalama.SpringSecurityJPA.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @Autowired
    UserRespository userRespository;

    @GetMapping("/")
    public String home(){
        return ("<h1>Home</h1>");
    }

    @GetMapping("/user")
    public String user(){
        return ("<h1>User</h1>");
    }

    @GetMapping("/admin")
    public String admin(){
        return ("<h1>Admin</h1>");
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        System.out.println("hello");
        return userRespository.save(user);
    }
}
