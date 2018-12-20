package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Laptop {

	public Laptop() {
		System.out.println("Laptop created");
	}

	@Autowired
	@Qualifier(value="seagate")
	private IHardDrive hd;
	private String laptopName;
	public IHardDrive getHd() {
		return hd;
	}
	public void setHd(IHardDrive hd) {
		this.hd = hd;
	}
	public String getLaptopName() {
		return laptopName;
	}
	public void setLaptopName(String laptopName) {
		this.laptopName = laptopName;
	}
	
	public void show()
	{
		System.out.println("Inside laptop");
		hd.printDriveConfig();
	}
}
