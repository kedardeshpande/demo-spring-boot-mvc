package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class Seagate implements IHardDrive {

	Seagate()
	{
		System.out.println("seagate HD created");
	}
	@Override
	public void printDriveConfig() {
		System.out.println("Seagate HD 1000 RPM 1 TB");

	}

}
