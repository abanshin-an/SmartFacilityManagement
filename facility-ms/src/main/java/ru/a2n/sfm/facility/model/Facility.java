package ru.a2n.sfm.facility.model;

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
@Table(name = "facility", schema = "facility_ms")
public class Facility {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "facility_name")
    private String name;

    @Column(name = "facility_address")
    private String address;
}
