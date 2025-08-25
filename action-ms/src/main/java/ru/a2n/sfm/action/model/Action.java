package ru.a2n.sfm.action.model;

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
@Table(name = "action", schema = "action_ms")
public class Action {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "responsibility_name", nullable = false)
    private String responsibilityName;

    @Column(name = "action_name", nullable = false)
    private String actionName;
}
