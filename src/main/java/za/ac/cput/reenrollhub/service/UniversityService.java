package za.ac.cput.reenrollhub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.ac.cput.reenrollhub.domain.entity.University;
import za.ac.cput.reenrollhub.repositoty.UniversityRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversityService implements IUniversityService {

    private final UniversityRepository universityRepository;
    @Override
    public University save(University university) {
        return universityRepository.saveAndFlush(university);
    }

    @Override
    public List<University> findAll() {
        return universityRepository.findAll();
    }

    public List<String> getAllUniversities() {
        return findAll().stream().map(University::getName).collect(Collectors.toList());
    }
}
