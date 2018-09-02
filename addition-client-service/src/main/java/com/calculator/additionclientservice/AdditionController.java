package com.calculator.additionclientservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/client-api")
public class AdditionController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/add/{a}/{b}")
	public String add(@PathVariable long a, @PathVariable long b) {

		String response = restTemplate.getForObject("http://addition-service:8000/api/add/" + a + "/" + b, String.class);

		return "Answer = " + response;
	}
}
