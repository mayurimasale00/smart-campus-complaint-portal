package smart_campus_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import smart_campus_backend.dto.ProfileResponse;
import smart_campus_backend.entity.User;
import smart_campus_backend.repository.UserRepository;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ProfileController {

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<ProfileResponse> getProfile(
            Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        ProfileResponse response = ProfileResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();

        return ResponseEntity.ok(response);
    }
}