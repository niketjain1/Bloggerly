package com.project.blogapp;

import com.project.blogapp.config.AppConstants;
import com.project.blogapp.entities.RoleEntity;
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

@SpringBootApplication
public class BlogappApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;
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

	@Override
	public void run(String... args) throws Exception {
		try {

			RoleEntity role = new RoleEntity();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ADMIN_USER");

			RoleEntity role1 = new RoleEntity();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("NORMAL_USER");

			List<RoleEntity> roles = List.of(role,role1);
			List<RoleEntity> savedRoles = roleRepository.saveAll(roles);
			savedRoles.forEach(r ->{
				System.out.println(r.getName());
			});

		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
