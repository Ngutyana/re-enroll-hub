package za.ac.cput.reenrollhub.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import za.ac.cput.reenrollhub.domain.enums.ApplicationStatus;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;
    private String password;
    private String university;
    private String name;
    private String surname;
    private String contactNumber;
    private String studentNumber;
    private String altEmail;
    private String address;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;
}
