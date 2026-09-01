package smart_campus_backend.dto;

import smart_campus_backend.enums.ComplaintCategory;
import smart_campus_backend.enums.ComplaintStatus;
import smart_campus_backend.enums.Priority;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplaintResponse {

    private Long id;

    private String title;

    private String description;

    private ComplaintCategory category;

    private Priority priority;

    private ComplaintStatus status;

    private String imageUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String studentName;

    private String studentEmail;
}