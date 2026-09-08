package smart_campus_backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponse {

    private Long id;

    private String name;

    private String email;

    private String role;

    private String phone;

    private String studentId;

    private String course;

    private String year;

    private String department;

    private String college;
}