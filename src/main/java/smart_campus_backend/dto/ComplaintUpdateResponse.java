package smart_campus_backend.dto;

import lombok.Builder;
import lombok.Data;

import smart_campus_backend.enums.ComplaintStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ComplaintUpdateResponse {

    private Long id;

    private String message;

    private ComplaintStatus status;

    private LocalDateTime createdAt;
}