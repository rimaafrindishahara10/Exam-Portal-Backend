package com.exam.services.impl;

import com.exam.models.User;
import com.exam.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= this.userRepo.findByUserName(username);

        if (user==null){
            System.out.println("User Not Found");
            throw  new UsernameNotFoundException("User Not Found");
        }

        return user;
    }
}
