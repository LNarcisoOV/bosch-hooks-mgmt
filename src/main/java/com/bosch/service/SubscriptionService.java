package com.bosch.service;

import java.util.List;
import java.util.Optional;

import com.bosch.model.Subscription;
import com.bosch.model.dto.SubscriptionDTO;

public interface SubscriptionService {
	
	public List<Subscription> getAll();

	public Optional<Subscription> getById(final Long id);
	
	public Optional<Subscription> create(final SubscriptionDTO subscriptionDTO);
	
	public Optional<Subscription> update(final Subscription subscription);
	
	public void delete(Long id);

}
