package com.om.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.om.app.model.Supplier;


public interface SupplierRepository extends JpaRepository<Supplier, Long> {

public Supplier findByEmail(String email);

}
