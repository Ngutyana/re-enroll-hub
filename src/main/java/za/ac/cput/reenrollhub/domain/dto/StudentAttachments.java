package za.ac.cput.reenrollhub.domain.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class StudentAttachments {
    private String type;
    private MultipartFile file;
}
