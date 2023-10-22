package za.ac.cput.reenrollhub.domain.entity.database;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UJDatabase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String studentName;
    private String studentSurname;
    private String studentNumber;
    private String studentPhoneNumber;
    private String studentEmail;
    private String altEmail;
    private String studentAddress;
    private String bursaryStatus;
    private String bursaryOrganisation;
    private Date registrationDate;
    private String qualification;
    private String yearOfQualification;
    private String faculty;
}
