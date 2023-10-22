package za.ac.cput.reenrollhub.repositoty.database;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.reenrollhub.domain.entity.database.CPUTDatabase;
import java.util.Optional;

public interface CPUTDatabaseRepository extends JpaRepository<CPUTDatabase, Long> {
    Optional<CPUTDatabase> findByStudentEmail(String studentEmail);
}
