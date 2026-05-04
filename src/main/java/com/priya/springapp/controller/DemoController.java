package com.priya.springapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/priya")
public class DemoController {

	@GetMapping("/isabadgirl")
	public String demo() {
		
		
		return "hii! the server is running, and we are testing!";
	}
}
