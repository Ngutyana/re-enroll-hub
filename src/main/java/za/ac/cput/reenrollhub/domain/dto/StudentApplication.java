package za.ac.cput.reenrollhub.domain.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class StudentApplication {
    private String studentEmail;
    private String facultyOfStudy;
    private String qualification;
    private String yearOfQualification;
    private int yearOfDeregistration;
    private String reasonOfDeregistration;
    private String storyBehindDeregistration;
    private String motivation;
    private MultipartFile[] supportingDocuments;
}
