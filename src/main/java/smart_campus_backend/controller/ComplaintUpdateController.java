package smart_campus_backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import smart_campus_backend.dto.ComplaintUpdateRequest;
import smart_campus_backend.dto.ComplaintUpdateResponse;
import smart_campus_backend.service.ComplaintUpdateService;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ComplaintUpdateController {

    private final ComplaintUpdateService complaintUpdateService;

    // Admin adds status update
    @PostMapping("/{complaintId}/updates")
    public ResponseEntity<ComplaintUpdateResponse> addUpdate(
            @PathVariable Long complaintId,
            @Valid @RequestBody ComplaintUpdateRequest request) {

        return new ResponseEntity<>(
                complaintUpdateService.addUpdate(
                        complaintId,
                        request
                ),
                HttpStatus.CREATED
        );
    }

    // Get complaint history
    @GetMapping("/{complaintId}/updates")
    public ResponseEntity<List<ComplaintUpdateResponse>> getUpdates(
            @PathVariable Long complaintId) {

        return ResponseEntity.ok(
                complaintUpdateService.getUpdates(complaintId)
        );
    }
}