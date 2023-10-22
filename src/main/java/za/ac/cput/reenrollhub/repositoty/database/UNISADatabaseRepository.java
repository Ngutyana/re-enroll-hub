package za.ac.cput.reenrollhub.repositoty.database;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.reenrollhub.domain.entity.database.UNISADatabase;
import java.util.Optional;

public interface UNISADatabaseRepository extends JpaRepository<UNISADatabase, Long> {
    Optional<UNISADatabase> findByStudentEmail(String studentEmail);
}
