package za.ac.cput.reenrollhub.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentInfo {
    public String name;
    public String surname;
    public String contactNumber;
    public String email;
    public String altEmail;
    public String university;
    public String address;
    public String applicationStatus;
}
