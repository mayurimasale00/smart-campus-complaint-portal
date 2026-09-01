package smart_campus_backend.repository;

import smart_campus_backend.entity.ComplaintUpdate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintUpdateRepository
        extends JpaRepository<ComplaintUpdate, Long> {

    List<ComplaintUpdate> findByComplaintIdOrderByCreatedAtDesc(Long complaintId);
}