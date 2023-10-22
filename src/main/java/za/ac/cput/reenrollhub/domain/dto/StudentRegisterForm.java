package za.ac.cput.reenrollhub.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentRegisterForm {
    public String email;
    public String university;
    public String password;
    public String confirmPassword;
}
