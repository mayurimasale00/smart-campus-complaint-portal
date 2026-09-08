package smart_campus_backend.service;

import smart_campus_backend.dto.AuthResponse;
import smart_campus_backend.dto.LoginRequest;
import smart_campus_backend.dto.RegisterRequest;
import smart_campus_backend.entity.User;
import smart_campus_backend.enums.Role;
import smart_campus_backend.repository.UserRepository;
import smart_campus_backend.security.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    // =========================
    // REGISTER
    // =========================

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .role(Role.STUDENT)

                // Profile information
                .phone(request.getPhone())
                .studentId(request.getStudentId())

                // Academic information
                .course(request.getCourse())
                .year(request.getYear())
                .department(request.getDepartment())
                .college(request.getCollege())

                .build();

        userRepository.save(user);


        // =========================
        // GENERATE JWT
        // =========================

        String token = jwtService.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        java.util.List.of()
                )
        );


        // =========================
        // RESPONSE
        // =========================

        return AuthResponse.builder()
                .token(token)
                .message("Registration successful")
                .role(user.getRole().name())
                .name(user.getName())
                .build();
    }


    // =========================
    // LOGIN
    // =========================

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(
                request.getEmail()
        ).orElseThrow(() ->
                new RuntimeException("User not found")
        );


        // =========================
        // GENERATE JWT
        // =========================

        String token = jwtService.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        java.util.List.of()
                )
        );


        // =========================
        // RESPONSE
        // =========================

        return AuthResponse.builder()
                .token(token)
                .message("Login successful")
                .role(user.getRole().name())
                .name(user.getName())
                .build();
    }
}