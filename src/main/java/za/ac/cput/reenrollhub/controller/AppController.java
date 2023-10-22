package za.ac.cput.reenrollhub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.reenrollhub.service.UniversityService;
import java.util.List;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class AppController {

    private final UniversityService universityService;

    @GetMapping("universities")
    public List<String> getUniversities() {
        return universityService.getAllUniversities();
    }

}
