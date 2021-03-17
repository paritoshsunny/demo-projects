package com.sunny.springboot.demo.mycoolapp.rest;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
	
	//expose end point for "/"
	@GetMapping("/")
	public String sayHello() {
		return "Hello World!! Time on the server is "+ LocalDateTime.now();
	}
	
	//expose end point for "workout"
	@GetMapping("/workout")
	public String getDailyWorkout() {
		return "Run a hard 5K";
	}
	
}
