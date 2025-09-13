package com.fees.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fees.demo.model.AdminModel;

public interface Adminrepository extends JpaRepository<AdminModel,Long> {
	Optional <AdminModel>findByUsername(String username);
}
