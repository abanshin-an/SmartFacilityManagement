package ru.a2n.sfm.agent.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import ru.a2n.sfm.message.model.Status;

@Entity
@Getter
@Setter
@ToString(exclude = "id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "outbox")
public class Outbox {
    @Id
    @UuidGenerator
    public String id;

    @Column(name = "facility_id")
    public String facilityId;

    @Column(name = "message_type")
    public String messageType;

    @Column(name = "paramValue")
    public long value;

    @Column(name = "unit")
    public String unit;

    @Column(name = "createdAt")
    public LocalDateTime createdAt;

    @Column(name = "reference_id")
    public String referenceId;

    @Column(name = "json")
    public String json;

    @Column(name = "saved_at")
    @CreationTimestamp
    public LocalDateTime savedAt;

    @Column(name = "sent_at")
    public LocalDateTime sentAt;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    public Status status;
}
