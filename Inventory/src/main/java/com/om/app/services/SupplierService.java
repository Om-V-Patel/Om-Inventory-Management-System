package com.om.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.om.app.model.Supplier;
import com.om.app.repo.SupplierRepository;

@Service
public class SupplierService {
	@Autowired
	private SupplierRepository SupplierRepository;
		
	public void saveImage(Supplier Supplier) {
		SupplierRepository.save(Supplier);	
	}

	public List<Supplier> getAllActiveSupplier() {
		return SupplierRepository.findAll();
	}

	public Optional<Supplier> getSupplierById(Long id) {
		return SupplierRepository.findById(id);
	}

}
