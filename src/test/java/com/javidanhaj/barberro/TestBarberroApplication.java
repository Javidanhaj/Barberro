package com.javidanhaj.barberro;

import org.springframework.boot.SpringApplication;

public class TestBarberroApplication {

	public static void main(String[] args) {
		SpringApplication.from(BarberroApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
