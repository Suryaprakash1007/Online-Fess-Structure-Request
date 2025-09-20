package com.fees.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fees.demo.model.Studentdetailmodel;

@Repository
public interface Studentdetailrepo extends JpaRepository<Studentdetailmodel, Long> {
    List<Studentdetailmodel> findByStatus(String status);

    List<Studentdetailmodel> findByRollno(String rollno); // ✅ FIXED
}
