package com.om.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.om.app.model.ImageGallery;
import com.om.app.model.Mongo;
import com.om.app.repo.MongoRepo;
import com.om.app.services.ImageGalleryService;

@Controller


public class OrderController {

	@Autowired
	private MongoRepo MRepo;
	@Autowired
	private ImageGalleryService imageGalleryService;

	@GetMapping("/mongo")
	public String showMongoForm(Model model) {
		model.addAttribute("mdata", new Mongo());
		return "MongoForm";
	}
	@PostMapping("/insertmongodata")
	public String addmdata(Mongo mdata) {
		System.out.print(mdata);
		MRepo.save(mdata);
		return "supplier_success";
	}
	@RequestMapping("/updatedata/{id}")
	public String updatedata(@PathVariable Long id) {
		ImageGallery imageGallery = imageGalleryService.getImageById(id);
		Mongo mongo = MRepo.findByRackid(id);
		double quantity = imageGallery.getQuantity()-mongo.getQuantity();
		imageGallery.setQuantity(quantity);
		imageGalleryService.saveImage(imageGallery);
		return "updated";
	}
	

}
