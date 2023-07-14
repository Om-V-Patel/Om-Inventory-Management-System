package com.om.app.contactus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "email", nullable = false, length = 20)
	private String email;
	@Column(name = "firstname", length = 20)
	private String firstName;
	@Column(name = "lastname", length = 20)
	private String lastName;
	@Column(name = "subject", nullable = false, length = 50)
	private String subject;
	@Column(name = "verification_code", length = 64)
	private String verificationCode;

	private boolean enabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Contact(Long id, String email, String firstName, String lastName, String subject, String verificationCode,
			boolean enabled) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.subject = subject;
		this.verificationCode = verificationCode;
		this.enabled = enabled;
	}

	public Contact() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", subject=" + subject + ", verificationCode=" + verificationCode + ", enabled=" + enabled + "]";
	}


}
