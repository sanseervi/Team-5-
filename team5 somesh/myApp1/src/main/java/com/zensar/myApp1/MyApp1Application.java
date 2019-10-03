package com.zensar.myApp1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MyApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(MyApp1Application.class, args);
	}
	@RequestMapping(value ="/myapp", method=RequestMethod.GET)
public String myApp()
{
	return "Responce is coming from my App";
}
}
