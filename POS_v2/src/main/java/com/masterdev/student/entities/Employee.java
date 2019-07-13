package com.masterdev.student.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@Column(name="employee_id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment",strategy="increment")
	public Long id;
	private String name;
	private String job;
	@Column(name="phone_number")
	private String phoneNumber;
	private String email;
	private String address;
	private String image;
	
	public Employee() {}
	
	public Employee(String name, String job, String phoneNumber, String eMail, String address, String image) {
		this.setName(name);
		this.setJob(job);
		this.setPhoneNumber(phoneNumber);
		this.setEmail(eMail);
		this.setAddress(address);
		this.setImage(image);
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
	
	public String getJob() {
		return job;
	}
	
	public void setJob(String job) {
		this.job = job;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String toString() {
		StringBuffer sf = new StringBuffer();
		sf.append("[ ");
		sf.append("ID: ");
		sf.append(this.getId());
		sf.append(", ");
		sf.append("Name: ");
		sf.append(this.getName());
		sf.append(", ");
		sf.append("Job: ");
		sf.append(this.getJob());
		sf.append(", ");
		sf.append("Phone Number: ");
		sf.append(this.getPhoneNumber());
		sf.append(", ");
		sf.append("E-mail: ");
		sf.append(this.getEmail());
		sf.append(", ");
		sf.append("Address: ");
		sf.append(this.getAddress());
		sf.append(", ");
		sf.append("Image: ");
		sf.append(this.getImage());
		sf.append(" ]");
		return sf.toString();
	}
}
