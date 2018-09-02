package com.calculator.additionservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdditionController {

	@GetMapping("/add/{a}/{b}")
	public long add(@PathVariable long a, @PathVariable long b) {
		return a + b;
	}
}
