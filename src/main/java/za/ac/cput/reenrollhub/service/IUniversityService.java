package za.ac.cput.reenrollhub.service;

import za.ac.cput.reenrollhub.domain.entity.University;
import java.util.List;

public interface IUniversityService {
    University save(University university);
    List<University> findAll();
}
