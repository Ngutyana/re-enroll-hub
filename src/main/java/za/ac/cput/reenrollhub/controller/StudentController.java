package za.ac.cput.reenrollhub.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.reenrollhub.domain.dto.StudentApplication;
import za.ac.cput.reenrollhub.domain.dto.StudentInfo;
import za.ac.cput.reenrollhub.domain.dto.StudentRegisterForm;
import za.ac.cput.reenrollhub.service.ApplicationService;
import za.ac.cput.reenrollhub.service.StudentService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/student/")
public class StudentController {
    private final StudentService studentService;
    private final ApplicationService applicationService;

    @GetMapping("login/{email}/{password}")
    public StudentInfo loginDonor(@PathVariable String email, @PathVariable String password) {
        return studentService.loginStudent(email, password);
    }

    @PostMapping("save")
    public StudentInfo register(@RequestBody StudentRegisterForm studentRegisterForm) {
        log.info("registering: {}", studentRegisterForm);
        return studentService.register(studentRegisterForm);
    }

    @PostMapping("application")
    public StudentInfo apply(@RequestParam("attachments") MultipartFile[] attachments,
                         @RequestParam("studentEmail") String studentEmail,
                         @RequestParam("facultyOfStudy") String facultyOfStudy,
                         @RequestParam("qualification") String qualification,
                         @RequestParam("yearOfQualification") String yearOfQualification,
                         @RequestParam("yearOfDeregistration") int yearOfDeregistration,
                         @RequestParam("reasonOfDeregistration") String reasonOfDeregistration,
                         @RequestParam("storyBehindDeregistration") String storyBehindDeregistration,
                         @RequestParam("motivation") String motivation) {

        StudentApplication studentApplication = StudentApplication.builder()
                .studentEmail(studentEmail)
                .facultyOfStudy(facultyOfStudy)
                .qualification(qualification)
                .yearOfQualification(yearOfQualification)
                .yearOfDeregistration(yearOfDeregistration)
                .reasonOfDeregistration(reasonOfDeregistration)
                .storyBehindDeregistration(storyBehindDeregistration)
                .motivation(motivation)
                .supportingDocuments(attachments)
                .build();

        log.info("application: {}", studentApplication);
        return applicationService.createApplication(studentApplication);
    }

    @GetMapping("cancel")
    public StudentInfo cancelApplication(@RequestParam("studentEmail") String studentEmail) {
        log.info("cancelling application for student: {}", studentEmail);
        return this.studentService.cancelApplication(studentEmail);
    }

    @GetMapping("update")
    public StudentInfo updateStudent(@RequestParam("studentEmail") String studentEmail,
                                     @RequestParam("altEmail") String altEmail,
                                     @RequestParam("contactNumber") String contactNumber,
                                     @RequestParam("address") String address) {
        return this.studentService.updateStudent(studentEmail, altEmail, contactNumber, address);
    }

}
