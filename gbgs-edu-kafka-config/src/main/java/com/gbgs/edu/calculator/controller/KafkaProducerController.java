package com.gbgs.edu.calculator.controller;

import com.gbgs.edu.calculator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gbgs.edu.calculator.service.KafKaProducerService;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaProducerController {
	private final KafKaProducerService producerService;

	@Autowired
	public KafkaProducerController(KafKaProducerService producerService) {
		this.producerService = producerService;
	}

	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
		this.producerService.sendMessage(message);
	}
	
	@PostMapping(value = "/createUser")
	public void sendMessageToKafkaTopic(
			@RequestParam("userId") long userId, 
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		
		User user = new User();
		user.setUserId(userId);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		
		this.producerService.saveCreateUserLog(user);
	}
}