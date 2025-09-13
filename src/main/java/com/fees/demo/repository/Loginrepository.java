package com.fees.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fees.demo.model.Loginmodel;
public interface Loginrepository extends JpaRepository<Loginmodel,Long>{
	Optional<Loginmodel> findByRollno(String rollno);
	List<Loginmodel> findByrole(String role);
	Loginmodel findByRollnoAndPassword(String rollno, String password);
}
