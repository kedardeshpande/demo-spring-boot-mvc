package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class Hitachi implements IHardDrive {

	Hitachi()
	{
		System.out.println("Hitachi HD created");
	}

	@Override
	public void printDriveConfig() {
		System.out.println("Hitachi HD 1000 RPM 1 TB");
	}

}
