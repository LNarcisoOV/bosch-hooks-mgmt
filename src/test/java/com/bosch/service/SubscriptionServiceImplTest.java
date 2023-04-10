package com.bosch.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.bosch.model.Subscription;
import com.bosch.model.dto.SubscriptionDTO;
import com.bosch.service.impl.SubscriptionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionServiceImplTest {

	@Mock
	private SubscriptionDAO subscriptionDAO;

	@Mock
	private SubscriptionService subscriptionService;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private SubscriptionServiceImpl subscriptionServiceImpl;

	@Test
	public void testSaveSubscription() {
		given(subscriptionDAO.save(any())).willReturn(subscription1());

		Optional<Subscription> subscriptionOPT = subscriptionServiceImpl.create(subscriptionDTO1());
		assertTrue(subscriptionOPT.isPresent());
		assertEquals(subscriptionOPT.get().getDescription(), "Desc 1");
		assertEquals(subscriptionOPT.get().isActive(), Boolean.TRUE);
	}

	@Test
	public void testUpdateSubscription() {
		given(subscriptionDAO.findById(anyLong())).willReturn(subscription1WithId());
		given(subscriptionDAO.save(any())).willReturn(subscription1());

		Subscription subscription = subscriptionServiceImpl.create(subscriptionDTO1()).get();

		subscription.setId(1L);
		subscription.setDescription("UPDATED DESC");
		subscription.setActive(Boolean.FALSE);

		Optional<Subscription> subscriptionUpdateOPT = subscriptionServiceImpl.update(subscription);

		assertTrue(subscriptionUpdateOPT.isPresent());
		assertEquals(subscriptionUpdateOPT.get().getDescription(), "UPDATED DESC");
		assertEquals(subscriptionUpdateOPT.get().isActive(), Boolean.FALSE);
	}

	@Test
	public void testDeletSubscription() {
		given(subscriptionDAO.findById(anyLong())).willReturn(subscription1WithId());
		given(subscriptionDAO.save(any())).willReturn(subscription1());

		subscriptionServiceImpl.create(subscriptionDTO1()).get();
		subscriptionServiceImpl.delete(1L);
		verify(subscriptionDAO, times(1)).delete(eq(subscription1WithId().get()));
	}

	private Subscription subscription1() {
		Subscription subscription = new Subscription();
		subscription.setDescription("Desc 1");
		subscription.setActive(Boolean.TRUE);
		return subscription;
	}

	private Optional<Subscription> subscription1WithId() {
		Subscription subscription = subscription1();
		subscription.setId(1L);
		return Optional.of(subscription);
	}

	private SubscriptionDTO subscriptionDTO1() {
		SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
		subscriptionDTO.setDescription("Desc 1");
		subscriptionDTO.setActive(Boolean.TRUE);
		return subscriptionDTO;
	}

}
