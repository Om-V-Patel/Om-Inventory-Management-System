package com.om.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.om.app.model.Supplier;
import com.om.app.repo.SupplierRepository;

@Controller
public class SupplierController {

	@Autowired
	SupplierRepository suppRepo;
	

	public SupplierController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/Supplier")
	public String showSupplierForm(Model model) {
		model.addAttribute("supplier", new Supplier());
		return "supplier";
	}

	@PostMapping("/insertsupplierdata")
	public String adddata(Supplier supplier) {
		System.out.print(supplier);
		suppRepo.save(supplier);

		return "supplier_success";
	}

	@GetMapping("/Suppliersdata")
	public String listSupplier(Model model) {
		List<Supplier> listSupplier = suppRepo.findAll();
		model.addAttribute("listSupplier", listSupplier);

		return "sdata";
	}


}
