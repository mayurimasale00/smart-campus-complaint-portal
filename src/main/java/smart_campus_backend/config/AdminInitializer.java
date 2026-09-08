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
public class AdminInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner createAdmin() {

        return args -> {

            String adminEmail = "admin@smartcampus.com";

            if (!userRepository.existsByEmail(adminEmail)) {

                User admin = User.builder()
                        .name("Smart Campus Admin")
                        .email(adminEmail)
                        .password(
                                passwordEncoder.encode("Admin@123")
                        )
                        .role(Role.ADMIN)
                        .phone("9999999999")
                        .studentId(null)
                        .course(null)
                        .year(null)
                        .department(null)
                        .college(null)
                        .build();

                userRepository.save(admin);

                System.out.println(
                        "======================================"
                );
                System.out.println(
                        "ADMIN ACCOUNT CREATED"
                );
                System.out.println(
                        "Email: admin@smartcampus.com"
                );
                System.out.println(
                        "Password: Admin@123"
                );
                System.out.println(
                        "======================================"
                );

            } else {

                System.out.println(
                        "Admin account already exists."
                );
            }
        };
    }
}