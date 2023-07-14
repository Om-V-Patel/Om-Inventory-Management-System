package com.om.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.om.app.model.ImageGallery;
import com.om.app.repo.ImageGalleryRepository;

@Service

public class ImageGalleryService {

	@Autowired
	private ImageGalleryRepository imageGalleryRepository;
	
	
	public void saveImage(ImageGallery imageGallery) {
		imageGalleryRepository.save(imageGallery);	
	}

	public List<ImageGallery> getAllActiveImages() {
		return imageGalleryRepository.findAll();
	}

	public  ImageGallery getImageById(Long id) {
		return imageGalleryRepository.findById(id).orElse(null);
	}
}

