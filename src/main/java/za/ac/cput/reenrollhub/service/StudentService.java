package za.ac.cput.reenrollhub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.ac.cput.reenrollhub.domain.dto.StudentInfo;
import za.ac.cput.reenrollhub.domain.dto.StudentRegisterForm;
import za.ac.cput.reenrollhub.domain.entity.Application;
import za.ac.cput.reenrollhub.domain.entity.Student;
import za.ac.cput.reenrollhub.domain.entity.database.CPUTDatabase;
import za.ac.cput.reenrollhub.domain.entity.database.RhodesDatabase;
import za.ac.cput.reenrollhub.domain.entity.database.UJDatabase;
import za.ac.cput.reenrollhub.domain.entity.database.UNISADatabase;
import za.ac.cput.reenrollhub.domain.enums.ApplicationStatus;
import za.ac.cput.reenrollhub.repositoty.ApplicationRepository;
import za.ac.cput.reenrollhub.repositoty.StudentRepository;
import za.ac.cput.reenrollhub.repositoty.database.CPUTDatabaseRepository;
import za.ac.cput.reenrollhub.repositoty.database.RhodesDatabaseRepository;
import za.ac.cput.reenrollhub.repositoty.database.UJDatabaseRepository;
import za.ac.cput.reenrollhub.repositoty.database.UNISADatabaseRepository;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;
    private final CPUTDatabaseRepository cputDatabaseRepository;
    private final RhodesDatabaseRepository rhodesDatabaseRepository;
    private final UJDatabaseRepository ujDatabaseRepository;
    private final UNISADatabaseRepository unisaDatabaseRepository;
    private final ApplicationRepository applicationRepository;

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student login(String email, String password) {
        return studentRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    public StudentInfo loginStudent(String email, String password) {
        Student student = login(email, password);
        if(student != null) {
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
       return StudentInfo.builder().build();
    }

    public StudentInfo register(StudentRegisterForm studentRegisterForm) {
        Student student = new Student();
        checkUniversityDB(studentRegisterForm.getUniversity(), studentRegisterForm.getEmail(), student);

        if(studentRepository.findByEmail(studentRegisterForm.getEmail()) != null) {
            throw new RuntimeException("Account already registered");
        }

        student.setEmail(studentRegisterForm.getEmail());
        student.setUniversity(studentRegisterForm.getUniversity());
        student.setPassword(studentRegisterForm.getPassword());
        student.setApplicationStatus(ApplicationStatus.NOT_SUBMITTED);
        save(student);

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

    private void checkUniversityDB(String university, String email, Student student) {
        switch (university) {
            case "Cape Peninsula University of Technology":
                CPUTDatabase cputDatabase = cputDatabaseRepository.findByStudentEmail(email)
                        .orElseThrow(() -> new RuntimeException("Student not found in Cape Peninsula University of Technology database"));
                student.setName(cputDatabase.getStudentName());
                student.setSurname(cputDatabase.getStudentSurname());
                student.setContactNumber(cputDatabase.getStudentPhoneNumber());
                student.setStudentNumber(cputDatabase.getStudentNumber());
                student.setAddress(cputDatabase.getStudentAddress());
                student.setAltEmail(cputDatabase.getAltEmail());
                break;
            case "Rhodes University":
                RhodesDatabase rhodesDatabase = rhodesDatabaseRepository.findByStudentEmail(email)
                        .orElseThrow(() -> new RuntimeException("Student not found in Rhodes University database"));
                student.setName(rhodesDatabase.getStudentName());
                student.setSurname(rhodesDatabase.getStudentSurname());
                student.setContactNumber(rhodesDatabase.getStudentPhoneNumber());
                student.setStudentNumber(rhodesDatabase.getStudentNumber());
                student.setAddress(rhodesDatabase.getStudentAddress());
                student.setAltEmail(rhodesDatabase.getAltEmail());
                break;
            case "University of Johannesburg":
                UJDatabase ujDatabase = ujDatabaseRepository.findByStudentEmail(email)
                        .orElseThrow(() -> new RuntimeException("Student not found in University of Johannesburg database"));
                student.setName(ujDatabase.getStudentName());
                student.setSurname(ujDatabase.getStudentSurname());
                student.setContactNumber(ujDatabase.getStudentPhoneNumber());
                student.setStudentNumber(ujDatabase.getStudentNumber());
                student.setAddress(ujDatabase.getStudentAddress());
                student.setAltEmail(ujDatabase.getAltEmail());
                break;
            case "University of South Africa":
                UNISADatabase unisaDatabase = unisaDatabaseRepository.findByStudentEmail(email)
                        .orElseThrow(() -> new RuntimeException("Student not found in University of Johannesburg database"));
                student.setName(unisaDatabase.getStudentName());
                student.setSurname(unisaDatabase.getStudentSurname());
                student.setContactNumber(unisaDatabase.getStudentPhoneNumber());
                student.setStudentNumber(unisaDatabase.getStudentNumber());
                student.setAddress(unisaDatabase.getStudentAddress());
                student.setAltEmail(unisaDatabase.getAltEmail());
                break;
            default: throw new RuntimeException(university + " is not integrated in our system yet");
        }

    }

    public StudentInfo cancelApplication(String studentEmail) {
        Student student = studentRepository.findByEmail(studentEmail);

        updateApplication(student.getEmail(), ApplicationStatus.CANCELLED);

        student.setApplicationStatus(ApplicationStatus.CANCELLED);
        student = update(student);

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

    public StudentInfo updateStudent(String studentEmail, String altEmail, String contactNumber, String address) {
        Student student = studentRepository.findByEmail(studentEmail);

        student.setAltEmail(altEmail);
        student.setContactNumber(contactNumber);
        student.setAddress(address);

        student = update(student);

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

    private void updateApplication(String email, ApplicationStatus status) {
        Application application = applicationRepository.findByStudentEmail(email);
        application.setStatus(status);
        applicationRepository.save(application);
    }

}
