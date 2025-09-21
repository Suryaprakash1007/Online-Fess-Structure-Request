package com.fees.demo.service;

import com.fees.demo.model.Studentdetailmodel;
import com.fees.demo.repository.Studentdetailrepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class Studentdetservice {

    private final Studentdetailrepo studentRepo;

    public Studentdetservice(Studentdetailrepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    // Save student details from form input (without file)
    public Studentdetailmodel saveStudent(Studentdetailmodel student) {
        if (student.getStatus() == null || student.getStatus().isEmpty()) {
            student.setStatus("PENDING");
        }
        return studentRepo.save(student);
    }

    // Save student details with file upload
    public Studentdetailmodel saveStudentWithFile(String rollno, String name, String reason, String email,
                                                  String course, String department, String year,
                                                  MultipartFile file) throws IOException {

        Studentdetailmodel student = new Studentdetailmodel();
        student.setRollno(rollno);
        student.setName(name);
        student.setReason(reason);
        student.setEmail(email);
        student.setCourse(course);
        student.setDepartment(department);
        student.setYear(year);
        student.setFileName(file.getOriginalFilename());
        student.setFileType(file.getContentType());
        student.setFileData(file.getBytes());
        student.setStatus("PENDING");

        return studentRepo.save(student);
    }

    // Get students by status
    public List<Studentdetailmodel> getPendingStudents() {
        return studentRepo.findByStatus("PENDING");
    }

    public List<Studentdetailmodel> getApprovedStudents() {
        return studentRepo.findByStatus("APPROVED");
    }

    public List<Studentdetailmodel> getRejectedStudents() {
        return studentRepo.findByStatus("REJECTED");
    }

    // Get student by ID
    public Optional<Studentdetailmodel> getStudentById(Long id) {
        return studentRepo.findById(id);
    }

    // Get student file by ID
    public Studentdetailmodel getFile(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    // Save student (duplicate of saveStudent — used by controller maybe)
    public void updateStudent(Studentdetailmodel student) {
        studentRepo.save(student);
    }

    // Get all requests made by roll number
    public List<Studentdetailmodel> getStudentsByRollno(String rollno) {
        return studentRepo.findByRollno(rollno);
    }
}
