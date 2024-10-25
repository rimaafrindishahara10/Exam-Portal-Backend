package com.exam;

import com.exam.models.Role;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamPortalApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ExamPortalApplication.class, args);
	}

	@Autowired
	private UserService userService;


	@Override
	public void run(String... args) throws Exception {

		System.out.println("Start Coding....");


//		User user = new User();
//		user.setUserName("rima");
//		user.setFirstName("Rima Afrin");
//		user.setLastName("Dishahara");
//		user.setEmail("rima@gmail.com");
//		user.setPassword("abc");
//
//		Role role = new Role();
//		role.setRole("ADMIN");
//
//
//
//		Set<UserRole> userRoleSet = new HashSet<>();
//		UserRole userRole = new UserRole();
//
//		userRole.setRole(role);
//		userRole.setUser(user);
//
//		userRoleSet.add(userRole);
//
//		User user1 = this.userService.createUser(user, userRoleSet);
//
//
//		System.out.println(user1.getUserName());
//
//



	}
}
