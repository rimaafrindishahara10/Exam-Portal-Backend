package com.exam.services.impl;

import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.repositories.RoleRepo;
import com.exam.repositories.UserRepo;
import com.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;


    //User-Create -Method

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User localUserName = userRepo.findByUserName(user.getUsername ());

        if(localUserName!=null){
            System.out.println("User has already here!!");
            throw new Exception("User has already present!!");
        }
        else {
            for (UserRole ur: userRoles){
                roleRepo.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            localUserName = this.userRepo.save(user);
        }


        return localUserName;
    }

    //Get User By userName:
    @Override
    public User getUser(String userName) {
        return this.userRepo.findByUserName(userName);
    }

    //Get-All Users
    @Override
    public List<User> getAllUser() {
        return this.userRepo.findAll();
    }

    //Update-User
    @Override
    public User updateUser(User user, Long id) {
       User userUpdate = this.userRepo.findById(id).get();
       userUpdate.setUserName(user.getUsername ());
       userUpdate.setFirstName(user.getFirstName());
       userUpdate.setLastName(user.getLastName());
       userUpdate.setEmail(user.getEmail());
       userUpdate.setPassword(user.getPassword());
       userUpdate.setPhone(user.getPhone());
       userUpdate.setProfile(user.getProfile());
       userUpdate.setActive(user.isActive());

        return this.userRepo.save(userUpdate);
    }

    //Delete-User:
    @Override
    public void deleteUser(Long id) {
        this.userRepo.deleteById(id);
    }


}
