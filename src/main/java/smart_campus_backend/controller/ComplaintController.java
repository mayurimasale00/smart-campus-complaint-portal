package smart_campus_backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import smart_campus_backend.dto.ComplaintRequest;
import smart_campus_backend.dto.ComplaintResponse;
import smart_campus_backend.dto.StatusUpdateRequest;
import smart_campus_backend.service.ComplaintService;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ComplaintController {

    private final ComplaintService complaintService;

    // Student creates complaint
    @PostMapping
    public ResponseEntity<ComplaintResponse> createComplaint(
            @Valid @RequestBody ComplaintRequest request,
            Authentication authentication) {

        return new ResponseEntity<>(
                complaintService.createComplaint(
                        request,
                        authentication.getName()
                ),
                HttpStatus.CREATED
        );
    }

    // Student gets own complaints
    @GetMapping("/my")
    public ResponseEntity<List<ComplaintResponse>> getMyComplaints(
            Authentication authentication) {

        return ResponseEntity.ok(
                complaintService.getMyComplaints(
                        authentication.getName()
                )
        );
    }

    // Get complaint by ID
    @GetMapping("/{id}")
    public ResponseEntity<ComplaintResponse> getComplaintById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                complaintService.getComplaintById(id)
        );
    }

    // Admin gets all complaints
    @GetMapping
    public ResponseEntity<List<ComplaintResponse>> getAllComplaints() {

        return ResponseEntity.ok(
                complaintService.getAllComplaints()
        );
    }

    // Admin updates complaint status
    @PutMapping("/{id}/status")
    public ResponseEntity<ComplaintResponse> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody StatusUpdateRequest request) {

        return ResponseEntity.ok(
                complaintService.updateComplaintStatus(
                        id,
                        request.getStatus()
                )
        );
    }

    // Delete complaint
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComplaint(
            @PathVariable Long id) {

        complaintService.deleteComplaint(id);

        return ResponseEntity.ok(
                "Complaint deleted successfully"
        );
    }
}