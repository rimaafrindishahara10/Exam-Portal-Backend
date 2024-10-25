package com.exam.services;

import com.exam.models.User;
import com.exam.models.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {

    //Create User->
    public User createUser(User user , Set<UserRole> userRoles) throws Exception;

    //Get User By userName:
    public User getUser(String userName);

    //Get All Users:
    public List<User> getAllUser();

    //Update-User:
    public User updateUser(User user, Long id);

    //Delete-User:
    public void deleteUser(Long id);


}
