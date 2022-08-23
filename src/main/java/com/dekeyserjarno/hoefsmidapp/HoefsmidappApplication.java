package com.dekeyserjarno.hoefsmidapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.dekeyserjarno.*")
public class HoefsmidappApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoefsmidappApplication.class, args);
	}



}
