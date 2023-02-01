package com.example.crisalisbackend;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.crisalisbackend.repository.TaxRepository;



@SpringBootApplication
public class CrisalisBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrisalisBackendApplication.class, args);
		//ArrayList<Integer> ids = new ArrayList<>();
		//ids.add(1);
		//TaxRepository.findByIds(ids);
	}

}
