package za.ac.cput.reenrollhub.repositoty.database;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.reenrollhub.domain.entity.database.RhodesDatabase;
import java.util.Optional;

public interface RhodesDatabaseRepository extends JpaRepository<RhodesDatabase, Long> {
    Optional<RhodesDatabase> findByStudentEmail(String studentEmail);
}
