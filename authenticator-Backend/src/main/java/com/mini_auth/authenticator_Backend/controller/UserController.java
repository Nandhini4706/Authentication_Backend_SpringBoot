package com.mini_auth.authenticator_Backend.controller;
import com.mini_auth.authenticator_Backend.model.User;
import com.mini_auth.authenticator_Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository repo;
// register
    // login
    //getAll
    //delete
    //update
    @PostMapping("/register")
    public String register(@RequestBody User user){
        repo.save(user);
        return "User saved in db";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        User u=repo.findByUsername(user.getUsername());
        if(u!=null && (u.getPassword().equals(user.getPassword()))){
            return "Login Success";
        }
        return "Invalid Username or Password";
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return repo.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        if(repo.existsById(id)){
            repo.deleteById(id);
            return "Deleted Successfully";
        }
        return "Username not exit";
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable int id, @RequestBody User user){
        Optional<User>optionalUser=repo.findById(id);
        if(optionalUser.isPresent()){
            User exist=optionalUser.get();
            exist.setPassword(user.getPassword());
            repo.save(exist);
            return "Updated Successfully";
        }
        return "User not found";
    }
}
