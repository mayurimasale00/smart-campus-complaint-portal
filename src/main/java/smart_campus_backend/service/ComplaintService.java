package smart_campus_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import smart_campus_backend.dto.ComplaintRequest;
import smart_campus_backend.dto.ComplaintResponse;
import smart_campus_backend.entity.Complaint;
import smart_campus_backend.entity.User;
import smart_campus_backend.enums.ComplaintStatus;
import smart_campus_backend.exception.ResourceNotFoundException;
import smart_campus_backend.repository.ComplaintRepository;
import smart_campus_backend.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;

    public ComplaintResponse createComplaint(
            ComplaintRequest request,
            String studentEmail) {

        User student = userRepository.findByEmail(studentEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        Complaint complaint = Complaint.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .category(request.getCategory())
                .priority(request.getPriority())
                .imageUrl(request.getImageUrl())
                .student(student)
                .build();

        Complaint savedComplaint =
                complaintRepository.save(complaint);

        return mapToResponse(savedComplaint);
    }

    public List<ComplaintResponse> getMyComplaints(
            String studentEmail) {

        User student = userRepository.findByEmail(studentEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        return complaintRepository.findByStudent(student)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ComplaintResponse getComplaintById(Long id) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Complaint not found with id: " + id));

        return mapToResponse(complaint);
    }

    public List<ComplaintResponse> getAllComplaints() {

        return complaintRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ComplaintResponse updateComplaintStatus(
            Long id,
            ComplaintStatus status) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Complaint not found with id: " + id));

        complaint.setStatus(status);

        Complaint updatedComplaint =
                complaintRepository.save(complaint);

        return mapToResponse(updatedComplaint);
    }

    public void deleteComplaint(Long id) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Complaint not found with id: " + id));

        complaintRepository.delete(complaint);
    }

    private ComplaintResponse mapToResponse(
            Complaint complaint) {

        return ComplaintResponse.builder()
                .id(complaint.getId())
                .title(complaint.getTitle())
                .description(complaint.getDescription())
                .category(complaint.getCategory())
                .priority(complaint.getPriority())
                .status(complaint.getStatus())
                .imageUrl(complaint.getImageUrl())
                .createdAt(complaint.getCreatedAt())
                .updatedAt(complaint.getUpdatedAt())
                .studentName(complaint.getStudent().getName())
                .studentEmail(complaint.getStudent().getEmail())
                .build();
    }
}