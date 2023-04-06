package com.bosch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosch.model.Subscription;
import com.bosch.service.SubscriptionService;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionController.class);
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@GetMapping("/")
	public ResponseEntity<Subscription> getAll(@PathVariable Long id) {
		return null;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Subscription> getById(@PathVariable Long id) {
		return null;
	}
	
	@PostMapping("/")
	public ResponseEntity<Subscription> create(@PathVariable Long id) {
		return null;
	}
	
	@PutMapping("/")
	public ResponseEntity<Subscription> update(@PathVariable Long id) {
		return null;
	}

	@DeleteMapping("/")
	public ResponseEntity<Subscription> delete(@PathVariable Long id) {
		return null;
	}
}
