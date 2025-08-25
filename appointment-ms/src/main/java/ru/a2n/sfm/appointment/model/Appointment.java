package ru.a2n.sfm.appointment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Setter
@ToString(exclude = "id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointment", schema = "appointment_ms")
public class Appointment {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "facility_id")
    private String facilityId;

    @Column(name = "person_id")
    private String personId;

    @ManyToOne
    @JoinColumn(name = "responsibility_id")
    private Responsibility responsibility;
}
