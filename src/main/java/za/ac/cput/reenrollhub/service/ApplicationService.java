package za.ac.cput.reenrollhub.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.reenrollhub.domain.dto.StudentApplication;
import za.ac.cput.reenrollhub.domain.dto.StudentInfo;
import za.ac.cput.reenrollhub.domain.entity.Application;
import za.ac.cput.reenrollhub.domain.entity.Attachment;
import za.ac.cput.reenrollhub.domain.entity.Student;
import za.ac.cput.reenrollhub.domain.enums.ApplicationStatus;
import za.ac.cput.reenrollhub.repositoty.ApplicationRepository;
import za.ac.cput.reenrollhub.repositoty.AttachmentRepository;
import za.ac.cput.reenrollhub.repositoty.StudentRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationService implements IApplicationService {

    private final ApplicationRepository applicationRepository;
    private final AttachmentRepository attachmentRepository;
    private final StudentRepository studentRepository;

    @Override
    public Application save(Application application) {
        return applicationRepository.save(application);
    }

    public StudentInfo createApplication(StudentApplication studentApplication) {
        Student student = studentRepository.findByEmail(studentApplication.getStudentEmail());

        Application application = Application.builder()
                .studentEmail(studentApplication.getStudentEmail())
                .facultyOfStudy(studentApplication.getFacultyOfStudy())
                .qualification(studentApplication.getQualification())
                .yearOfQualification(studentApplication.getYearOfQualification())
                .yearOfDeregistration(studentApplication.getYearOfDeregistration())
                .reasonOfDeregistration(studentApplication.getReasonOfDeregistration())
                .storyBehindDeregistration(studentApplication.getStoryBehindDeregistration())
                .storyBehindDeregistration(studentApplication.getStoryBehindDeregistration())
                .motivation(studentApplication.getMotivation())
                .status(ApplicationStatus.PENDING)
                .university(student.getUniversity())
                .statusDate(new Date())
                .supportingDocuments(getAttachments(studentApplication.getSupportingDocuments(), student.getStudentNumber()))
                .build();

        applicationRepository.save(application);
        student.setApplicationStatus(application.getStatus());
        student = studentRepository.save(student);

        return StudentInfo.builder()
                .name(student.getName())
                .surname(student.getSurname())
                .contactNumber(student.getContactNumber())
                .email(student.getEmail())
                .altEmail(student.getAltEmail())
                .university(student.getUniversity())
                .address(student.getAddress())
                .applicationStatus(student.getApplicationStatus().name())
                .build();
    }

    private List<Attachment> getAttachments(MultipartFile[] correspondentDocuments, String studentNumber) {
        List<Attachment> attachments = new ArrayList<>();
        Arrays.stream(correspondentDocuments).forEach(file -> {
            Attachment attachment = Attachment.builder()
                    .type(file.getContentType())
                    .location(storeFile(file, studentNumber))
                    .build();
            attachments.add(attachmentRepository.save(attachment));
        });
        return attachments;
    }

    private String storeFile(MultipartFile file, String studentNumber) {
        String destination = "attachments/" + studentNumber + "/";
        File directory = new File(destination);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            String location = destination + file.getOriginalFilename();
            FileOutputStream fos = new FileOutputStream(location);

            byte[] byteStream = file.getBytes();
            fos.write(byteStream);
            fos.close();

            log.info("File saved to attachments folder successfully");
            return location;
        } catch (IOException e){
            log.info("File could NOT be saved to attachments folder");
            return "**no file**";
        }

    }

}
