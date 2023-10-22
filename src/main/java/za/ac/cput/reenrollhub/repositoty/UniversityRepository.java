package za.ac.cput.reenrollhub.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.reenrollhub.domain.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
}
