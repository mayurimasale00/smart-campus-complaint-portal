package smart_campus_backend.entity;

import smart_campus_backend.enums.ComplaintStatus;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "complaint_updates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplaintUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "complaint_id")
    private Complaint complaint;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}