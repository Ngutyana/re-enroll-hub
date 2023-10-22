package za.ac.cput.reenrollhub.service;

import za.ac.cput.reenrollhub.domain.entity.Student;

public interface IStudentService {
    Student save(Student student);
    Student update(Student student);
    Student login(String email, String password);
}
