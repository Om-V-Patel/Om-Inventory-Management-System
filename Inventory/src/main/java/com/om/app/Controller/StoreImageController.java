package com.om.app.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.om.app.model.ImageGallery;
import com.om.app.services.ImageGalleryService;;

@Controller
public class StoreImageController {

	@Value("${uploadDir}")
	private String uploadFolder;

	@Autowired
	private ImageGalleryService imageGalleryService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/addproducts")
	public String addProductPage() {
		return "index2.html";
	}
	

	@PostMapping("/image/saveImageDetails")
	public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("name") String name,
			@RequestParam("price") double price, @RequestParam("quantity") double quantity,
			@RequestParam("description") String description, Model model, HttpServletRequest request,
			final @RequestParam("image") MultipartFile file) {
		try {
			// String uploadDirectory = System.getProperty("user.dir") + uploadFolder;
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory:: " + uploadDirectory);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + file.getOriginalFilename());
			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
				return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName,
						HttpStatus.BAD_REQUEST);
			}
			String[] names = name.split(",");
			String[] descriptions = description.split(",");
			Date createDate = new Date();
			log.info("Name: " + names[0] + " " + filePath);
			log.info("description: " + descriptions[0]);
			log.info("price: " + price);
			log.info("quantity: " + quantity);
			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("Folder Created");
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				log.info("in catch");
				e.printStackTrace();
			}
			byte[] imageData = file.getBytes();
			ImageGallery imageGallery = new ImageGallery();
			imageGallery.setName(names[0]);
			imageGallery.setImage(imageData);
			imageGallery.setPrice(price);
			imageGallery.setQuantity(quantity);
			imageGallery.setDescription(descriptions[0]);
			imageGallery.setCreateDate(createDate);
			imageGalleryService.saveImage(imageGallery);
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
				
			log.info("Exception: " + e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
		}
	}

	@GetMapping("/image/display/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response) throws ServletException, IOException { 
	    log.info("Id :: " + id);
	    ImageGallery imageGallery = imageGalleryService.getImageById(id);
	    if (imageGallery != null && imageGallery.getImage() != null) {
	        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	        response.getOutputStream().write(imageGallery.getImage());
	        response.getOutputStream().close();
	    } else {
	        // Handle the case when imageGallery or its image is null
	        // Return an appropriate error response or handle it as needed
	        response.sendError(HttpServletResponse.SC_NOT_FOUND);
	    }
	}


	@GetMapping("/image/imageDetails")
	String showProductDetails(@RequestParam("id") Long id, ImageGallery imageGallery, Model model) {
		try {
			log.info("Id :: " + id);
			if (id != 0) {
				imageGallery = imageGalleryService.getImageById(id);

				log.info("products :: " + imageGallery);
				if (imageGallery != null) {
					model.addAttribute("id", imageGallery.getId());
					model.addAttribute("description", imageGallery.getDescription());
					model.addAttribute("name", imageGallery.getName());
					model.addAttribute("price", imageGallery.getPrice());
					model.addAttribute("quantity", imageGallery.getQuantity());
					return "imagedetails";
				}
				return "redirect:/home";
			}
			return "redirect:/home";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/home";
		}
	}

	@GetMapping("/image/show")
	String show(Model map) {
		List<ImageGallery> images = imageGalleryService.getAllActiveImages();
		map.addAttribute("images", images);
		return "images";
	}
}
