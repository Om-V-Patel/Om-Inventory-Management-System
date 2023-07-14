package com.om.app.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventorydatas")
public class Mongo {
	  @Id
	  public long rackid;
	  public String name;
	  public double quantity;
	public long getRackid() {
		return rackid;
	}
	public void setRackid(long rackid) {
		this.rackid = rackid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	public Mongo(int rackid, String name, double quantity) {
		super();
		this.rackid = rackid;
		this.name = name;
		this.quantity = quantity;
	}
	public Mongo() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Mongo [rackid=" + rackid + ", name=" + name + ", quantity=" + quantity + "]";
	}
	  
	  
}
