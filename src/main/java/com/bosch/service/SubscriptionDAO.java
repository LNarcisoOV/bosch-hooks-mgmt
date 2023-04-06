package com.bosch.service;

import org.springframework.data.repository.CrudRepository;

import com.bosch.model.Subscription;

public interface SubscriptionDAO extends CrudRepository<Subscription, Long>{

}
