package com.example.springrabbitmqproducer.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrabbitmqproducer.config.RabbitMqConfig;

@RestController
public class RabbitMqProducerController {

	@Autowired
	private RabbitTemplate template;
	
	@PostMapping("/publish")
	public String publicMessage() {
		String message="Message published successfully";
		template.convertAndSend(RabbitMqConfig.exchange, RabbitMqConfig.routingKey, message);
		return message;
	}
	
}
