package com.om.app.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.om.app.contactus.Contact;
import com.om.app.contactus.ContactRepository;
import com.om.app.model.User;
import com.om.app.repo.UserRepository;

@Controller

public class AppController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	ContactRepository contRepo;

	@Autowired
	private JavaMailSender mailSender;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/1")
	public String viewMainPage() {
		return "index.html";
	}

	@GetMapping("/")
	public String viewHomePage() {
		return "index1.html";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";

	}

	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		userRepo.save(user);

		return "register_success";
	}

	@GetMapping("/home")
	public String showpage() {
		return "Outhlogged";
	}
	@GetMapping("/demo")
	public String showdemo() {
		return "successor";
	}

	@GetMapping("/AboutUs")
	public String showAboutpage() {
		return "AboutUspage";
	}

	@GetMapping("/Contact")
	public String showcontactForm(Model model) {
		model.addAttribute("contact", new Contact());
		return "Contactpage";
	}

	@RequestMapping("/insertdata")
	public String adddata(Contact contact) {
		System.out.print(contact);
		contRepo.save(contact);

		return "contact_success";
	}

	@GetMapping("/sendemail")
	public String sendPlainTextEmail(Model model) {

		String from = "ompatel22002@gmail.com";
		String to = "ompatel22002@gmail.com";

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject("Contact Details of Our Company");
		message.setText(
				"Hello ! This email from Om Patel and For Contact :- 9510463928 and For Mail :- omvpatel1822002@gmail.com");

		mailSender.send(message);

		model.addAttribute("message", "A plain text email has been sent");
		return "emailsend";
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "userspage";
	}

	

}
