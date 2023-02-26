package com.project.blogapp;

import com.project.blogapp.config.AppConstants;
import com.project.blogapp.entities.Role;
import com.project.blogapp.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication(scanBasePackages = "com.project.blogapp")
public class BlogappApplication {
	public static void main(String[] args) {
		SpringApplication.run(BlogappApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Override
//	public void run(String... args) throws Exception {
//		try {
//
//			Role role = new Role();
//			role.setId(AppConstants.ADMIN_USER);
//			role.setName("ROLE_ADMIN");
//
//			Role role1 = new Role();
//			role1.setId(AppConstants.NORMAL_USER);
//			role1.setName("ROLE_NORMAL");
//
//			List<Role> roles = List.of(role,role1);
//			List<Role> savedRoles = roleRepository.saveAll(roles);
//			savedRoles.forEach(r ->{
//				System.out.println(r.getName());
//			});
//
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//
//	}
}
