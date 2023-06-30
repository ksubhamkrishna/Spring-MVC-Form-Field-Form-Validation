package com.luv2code.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController { // Controller class with the annotation Controller

	@RequestMapping("/")
	public String showPage() {
		
		return "main-menu";
	}
	
	
}
