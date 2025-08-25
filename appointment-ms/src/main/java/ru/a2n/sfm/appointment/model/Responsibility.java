package ru.a2n.sfm.appointment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "responsibility", schema = "appointment_ms")
public class Responsibility {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "responsibility_name")
    private String responsibilityName;
}
