package com.bosch.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bosch.model.Subscription;

public interface SubscriptionDAO extends JpaRepository<Subscription, Long>{

}
