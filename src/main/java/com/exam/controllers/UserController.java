package com.exam.controllers;

import com.exam.models.Role;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //Create-> User
    @PostMapping("/")
    public User createUser(@RequestBody User user ) throws Exception {
        Set<UserRole> userRoles = new HashSet<>();



        Role role = new Role();
        role.setRole("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);

        userRoles.add(userRole);


        return this.userService.createUser(user, userRoles);
    }

    //Get-User By userName:
    @GetMapping("/{userName}")
    public User getUser(@PathVariable String userName){
        return this.userService.getUser(userName);
    }

    //Get-All Users
    @GetMapping("/")
    public List<User> getAllUser(){
       return this.userService.getAllUser();
    }

    //Update-User:
    @PutMapping("{id}")
    public User updateUser(@RequestBody User user,  @PathVariable Long id){
        return this.userService.updateUser(user,id);

    }

    //Delete-User:
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id){
        this.userService.deleteUser(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }





}