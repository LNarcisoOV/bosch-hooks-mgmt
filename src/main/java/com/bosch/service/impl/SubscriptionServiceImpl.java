package com.bosch.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bosch.model.Subscription;
import com.bosch.model.dto.SubscriptionDTO;
import com.bosch.service.SubscriptionDAO;
import com.bosch.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

	@Autowired
	private SubscriptionDAO subscriptionDao;	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<Subscription> getAll() {
		return subscriptionDao.findAll();
	}

	@Override
	public Optional<Subscription> getById(final Long id) {
		return subscriptionDao.findById(id);
	}
	
	@Override
	public Optional<Subscription> create(final SubscriptionDTO subscriptionDTO) {
		final Subscription subscription = modelMapper.map(subscriptionDTO, Subscription.class);
		final Subscription subscriptionDB = subscriptionDao.save(subscription);
		return Optional.of(subscriptionDB);
	}
	
	@Override
	public Optional<Subscription> update(final SubscriptionDTO subscriptionDTO) {
		final Optional<Subscription> subscriptionOpt = getById(subscriptionDTO.getId());
		if(subscriptionOpt.isPresent()) {
			return create(subscriptionDTO);
		}
		return Optional.empty();
	}
	
	@Override
	public void delete(Long id) {
		final Optional<Subscription> subscriptionOpt = getById(id);
		if(subscriptionOpt.isPresent()) {
			subscriptionDao.delete(subscriptionOpt.get());
		}
	}
}
