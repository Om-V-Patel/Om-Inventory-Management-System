package com.om.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "supplier")
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true, length = 45)
	private String name;
	@Column(nullable = false, unique = true, length = 45)
	private String email;
	@Column(nullable = false, unique = true, length = 45)
	private String description;
  

	@Column(nullable = false, unique = true, length = 45)
	private String productname;

	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	



	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", email=" + email + ", description=" + description
				+ ", productname=" + productname + "]";
	}

	

}
