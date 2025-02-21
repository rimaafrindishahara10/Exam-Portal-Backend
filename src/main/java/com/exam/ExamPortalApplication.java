package com.exam;

import com.exam.repositories.RoleRepo;
import com.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ExamPortalApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ExamPortalApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RoleRepo roleRepo;


	@Override
	public void run(String... args) throws Exception {

		System.out.println("Start Coding....");

//
//		User user = new User();
//		user.setUserName("rima");
//		user.setFirstName("Rima Afrin");
//		user.setLastName("Dishahara");
//		user.setEmail("rima@gmail.com");
//		user.setPassword(this.bCryptPasswordEncoder.encode ( "123" ));
//
//		Role role = new Role();
//		role.setRoleId ( 10L );
//		role.setRoleName ( "ADMIN" );
//		roleRepo.save ( role );
//
//
//
//		Set<UserRole> userRoleSet = new HashSet<> ();
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
//		System.out.println(user1.getUsername ());




	}
}
