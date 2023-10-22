package za.ac.cput.reenrollhub.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.reenrollhub.domain.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Application findByStudentEmail(String studentEmail);
}
