package com.fees.demo.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fees.demo.model.Loginmodel;
import com.fees.demo.model.Studentdetailmodel;
import com.fees.demo.repository.Loginrepository;
import com.fees.demo.repository.Studentdetailrepo;

@Service
public class Studentservice {

    private final Loginrepository studentrepo;
    private final Studentdetailrepo studetailrepo;

    // constructor injection for both repos
    public Studentservice(Loginrepository studentrepo, Studentdetailrepo studetailrepo) {
        this.studentrepo = studentrepo;
        this.studetailrepo = studetailrepo;
    }

    // Save Loginmodel (students table)
    public Loginmodel saveStudent(Loginmodel student) {
        return studentrepo.save(student);
    }

    // Get Studentdetailmodel by id (studentsdetails table)
    public Optional<Studentdetailmodel> getStudentById(Long id) {
        return studetailrepo.findById(id);
    }

    // Find all logins with role = 'student'
    public List<Loginmodel> findByrole() {
        return studentrepo.findByrole("student");
    }
    public boolean updateRequest(Long id, Loginmodel updatedRequest) {
        Loginmodel existingRequest = studentrepo.findById(id).orElse(null);
        if (existingRequest == null) {
            return false;
        }

        // Update fields (you can customize this as needed)
        existingRequest.setRollno(updatedRequest.getRollno());
        existingRequest.setPassword(updatedRequest.getPassword());
        existingRequest.setName(updatedRequest.getName());
        existingRequest.setDepartment(updatedRequest.getDepartment());
        existingRequest.setYear(updatedRequest.getYear());
        existingRequest.setCourse(updatedRequest.getCourse());
        existingRequest.setEmail(updatedRequest.getEmail());
        existingRequest.setRole(updatedRequest.getRole());
        
       
        
        
        

        

        studentrepo.save(existingRequest);
        return true;
    }

    public boolean existsById(Long id) {
        return studentrepo.existsById(id);  // this checks DB
    }


	public void deleteById(Long id) {
		studentrepo.deleteById(id);
	}

	public Loginmodel getStudentByRollno(String rollno) throws Exception {
        return studentrepo.findByRollno(rollno)
                .orElseThrow(() -> new Exception("Student not found with regno: " + rollno));
    }

	public Loginmodel findByRollnoAndPassword(String rollno, String password) {
		// TODO Auto-generated method stub
		return studentrepo.findByRollnoAndPassword(rollno,password);
	}

}
