package com.bosch.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosch.model.Subscription;
import com.bosch.model.dto.SubscriptionDTO;
import com.bosch.service.SubscriptionService;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionController.class);

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/")
	public ResponseEntity<List<Subscription>> getAll() {
		return new ResponseEntity<List<Subscription>>(subscriptionService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SubscriptionDTO> getById(@PathVariable Long id) {
		Optional<Subscription> subscriptionOpt = subscriptionService.getById(id);
		if (subscriptionOpt.isPresent()) {
			final SubscriptionDTO subscriptionDTO = modelMapper.map(subscriptionOpt.get(), SubscriptionDTO.class);
			return new ResponseEntity<SubscriptionDTO>(subscriptionDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	public ResponseEntity<SubscriptionDTO> create(@RequestBody SubscriptionDTO subscriptionDTO) {
		try {
			Optional<Subscription> subscriptionOpt = subscriptionService.create(subscriptionDTO);
			if (subscriptionOpt.isPresent()) {
				final SubscriptionDTO subscriptionDBDTO = modelMapper.map(subscriptionOpt.get(), SubscriptionDTO.class);
				return new ResponseEntity<SubscriptionDTO>(subscriptionDBDTO, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (RuntimeException runtimeException) {
			LOGGER.error("Error to create subscription: {} ", runtimeException.getMessage());
			throw new RuntimeException("An exception occurs;");
		}
	}

	@PutMapping("/")
	public ResponseEntity<SubscriptionDTO> update(@RequestBody Subscription subscription) {
		try {
			Optional<Subscription> subscriptionOpt = subscriptionService.update(subscription);
			if (subscriptionOpt.isPresent()) {
				final SubscriptionDTO subscriptionDBDTO = modelMapper.map(subscriptionOpt.get(), SubscriptionDTO.class);
				return new ResponseEntity<SubscriptionDTO>(subscriptionDBDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (RuntimeException runtimeException) {
			LOGGER.error("Error to update subscription: {} ", runtimeException.getMessage());
			throw new RuntimeException("An exception occurs;");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		try {
			subscriptionService.delete(id);
			return new ResponseEntity(HttpStatus.OK);
		} catch (RuntimeException runtimeException) {
			LOGGER.error("Error to delete subscription: {} ", runtimeException.getMessage());
			throw new RuntimeException("An exception occurs;");
		}

	}
}
