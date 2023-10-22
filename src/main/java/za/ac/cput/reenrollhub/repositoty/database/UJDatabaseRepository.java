package za.ac.cput.reenrollhub.repositoty.database;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.reenrollhub.domain.entity.database.UJDatabase;
import java.util.Optional;

public interface UJDatabaseRepository extends JpaRepository<UJDatabase, Long> {
    Optional<UJDatabase> findByStudentEmail(String studentEmail);
}
