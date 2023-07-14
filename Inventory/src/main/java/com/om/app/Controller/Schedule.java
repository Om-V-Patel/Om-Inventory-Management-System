package com.om.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.om.app.model.ImageGallery;
import com.om.app.services.ImageGalleryService;

@Controller
@EnableScheduling
public class Schedule {
	@Value("${sender.mail}")
	private String from;
	@Value("${receiver.mail}")
	private String to;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private ImageGalleryService imageGalleryService;
//	
//	@Scheduled(fixedDelay = 60*60*1000)
//	public void sendEmail() {
//		List<ImageGallery> images = imageGalleryService.getAllActiveImages();
//		for(ImageGallery image : images) {
//			if(image.getQuantity()<5) {
//				
//				SimpleMailMessage message = new SimpleMailMessage();
//				message.setFrom(from);
//				message.setTo(to);
//				message.setSubject("Check the Products Quantity");
//				message.setText(
//						"Hello ! This email from Om Patel ,Please Check the inventory Product Quantity which Product Quantity less than 5.  For Contact :- 9510463928 and For Mail :- ompatel22002@gmail.com");
//				mailSender.send(message);
//			}
//		}
//	
//	}
}
