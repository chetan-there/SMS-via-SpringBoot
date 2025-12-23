package com.ct.studentmanagment.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ct.studentmanagment.StudentmanagmentApplication;
import com.ct.studentmanagment.model.Users;
import com.ct.studentmanagment.repository.UserRepository;

@Configuration
public class DataIntializer {

    private final UserRepository userRepository;

    private final StudentmanagmentApplication studentmanagmentApplication;

    DataIntializer(StudentmanagmentApplication studentmanagmentApplication, UserRepository userRepository) {
        this.studentmanagmentApplication = studentmanagmentApplication;
        this.userRepository = userRepository;
    }
	
	@Bean
	CommandLineRunner loadsampledata(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return 	args ->{
			if (!userRepository.existsByUsername("Admin")) {			
			Users users = new Users();
			users.setUsername("Admin");
			users.setPassword(passwordEncoder.encode("admin@123"));
			users.setActive(true);
			userRepository.save(users);
			}
		};
	}

}
