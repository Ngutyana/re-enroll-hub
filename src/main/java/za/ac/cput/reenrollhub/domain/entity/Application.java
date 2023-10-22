package za.ac.cput.reenrollhub.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import za.ac.cput.reenrollhub.domain.enums.ApplicationStatus;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String studentEmail;
    private String facultyOfStudy;
    private String qualification;
    private String yearOfQualification;
    private int yearOfDeregistration;
    private String reasonOfDeregistration;
    private String storyBehindDeregistration;
    private String motivation;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    private Date statusDate;
    private String updatedBy;
    private String university;
    @OneToMany()
    @JoinColumn(referencedColumnName = "id")
    private List<Attachment> supportingDocuments;
}
