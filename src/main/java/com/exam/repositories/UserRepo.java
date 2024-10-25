package com.exam.repositories;

import com.exam.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
      public User findByUserName(String userName);


}
