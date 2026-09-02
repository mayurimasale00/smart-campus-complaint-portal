package smart_campus_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import smart_campus_backend.dto.ComplaintUpdateRequest;
import smart_campus_backend.dto.ComplaintUpdateResponse;
import smart_campus_backend.entity.Complaint;
import smart_campus_backend.entity.ComplaintUpdate;
import smart_campus_backend.exception.ResourceNotFoundException;
import smart_campus_backend.repository.ComplaintRepository;
import smart_campus_backend.repository.ComplaintUpdateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintUpdateService {

    private final ComplaintRepository complaintRepository;
    private final ComplaintUpdateRepository complaintUpdateRepository;

    public ComplaintUpdateResponse addUpdate(
            Long complaintId,
            ComplaintUpdateRequest request) {

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Complaint not found with id: " + complaintId
                        )
                );

        // Update the complaint's current status
        complaint.setStatus(request.getStatus());
        complaintRepository.save(complaint);

        // Create history record
        ComplaintUpdate update = ComplaintUpdate.builder()
                .message(request.getMessage())
                .status(request.getStatus())
                .complaint(complaint)
                .build();

        ComplaintUpdate savedUpdate =
                complaintUpdateRepository.save(update);

        return mapToResponse(savedUpdate);
    }

    public List<ComplaintUpdateResponse> getUpdates(
            Long complaintId) {

        // Make sure complaint exists
        complaintRepository.findById(complaintId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Complaint not found with id: " + complaintId
                        )
                );

        return complaintUpdateRepository
                .findByComplaintIdOrderByCreatedAtDesc(complaintId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ComplaintUpdateResponse mapToResponse(
            ComplaintUpdate update) {

        return ComplaintUpdateResponse.builder()
                .id(update.getId())
                .message(update.getMessage())
                .status(update.getStatus())
                .createdAt(update.getCreatedAt())
                .build();
    }
}