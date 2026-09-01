package smart_campus_backend.repository;

import smart_campus_backend.entity.Complaint;
import smart_campus_backend.entity.User;
import smart_campus_backend.enums.ComplaintStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByStudent(User student);

    List<Complaint> findByStatus(ComplaintStatus status);

    long countByStatus(ComplaintStatus status);
}