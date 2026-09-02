package smart_campus_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import smart_campus_backend.enums.ComplaintStatus;

@Data
public class ComplaintUpdateRequest {

    @NotNull(message = "Status is required")
    private ComplaintStatus status;

    @NotBlank(message = "Message is required")
    private String message;
}