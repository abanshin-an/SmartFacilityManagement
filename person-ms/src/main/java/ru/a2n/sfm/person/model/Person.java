package ru.a2n.sfm.person.model;

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
@Table(name = "person", schema = "person_ms")
public class Person {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "person_full_name")
    private String fullName;

    @Column(name = "person_login")
    private String login;

    @Column(name = "person_email")
    private String email;

    @Column(name = "person_phone")
    private String phone;

    @Column(name = "person_password")
    private String password;

    public Person(String fullName, String login, String email, String phone, String password) {
        this.fullName = fullName;
        this.login = login;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}
