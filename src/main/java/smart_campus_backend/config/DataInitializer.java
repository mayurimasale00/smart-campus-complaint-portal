package smart_campus_backend.config;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import smart_campus_backend.entity.User;
import smart_campus_backend.enums.Role;
import smart_campus_backend.repository.UserRepository;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner createAdmin() {

        return args -> {

            String adminEmail = "admin@smartcampus.com";

            if (!userRepository.existsByEmail(adminEmail)) {

                User admin = User.builder()
                        .name("Admin")
                        .email(adminEmail)
                        .password(passwordEncoder.encode("admin123"))
                        .role(Role.ADMIN)
                        .build();

                userRepository.save(admin);

                System.out.println("====================================");
                System.out.println("DEFAULT ADMIN CREATED");
                System.out.println("Email: admin@smartcampus.com");
                System.out.println("Password: admin123");
                System.out.println("====================================");
            }
        };
    }
}