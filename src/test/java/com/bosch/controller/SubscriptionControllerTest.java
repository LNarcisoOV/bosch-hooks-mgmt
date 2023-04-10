package com.bosch.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bosch.model.Subscription;
import com.bosch.model.dto.SubscriptionDTO;
import com.bosch.service.SubscriptionService;

@RunWith(SpringRunner.class)
@WebMvcTest(SubscriptionController.class)
public class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ModelMapper modelMapper;
    
    @MockBean
    private SubscriptionService subscriptionService;
    

    @Test
    public void findSubscriptionById() throws Exception {
        given(subscriptionService.getById(Mockito.anyLong())).willReturn(subscription1());
        given(modelMapper.map(Mockito.any(), Mockito.any())).willReturn(subscription1DTO());

        mockMvc.perform(get("/subscriptions/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("description", is("Desc 1")))
                .andExpect(jsonPath("active", is(Boolean.TRUE)));
    }
    
    @Test
    public void findByIdUsingInvalidId() throws Exception {
    	 given(subscriptionService.getById(3L)).willReturn(Optional.empty());

         mockMvc.perform(get("/subscriptions/3")
                 .contentType(MediaType.APPLICATION_JSON))
                 .andExpect(status().isNotFound());
    }
    
    @Test
    public void saveSubscription() throws Exception {
        given(subscriptionService.create(Mockito.any())).willReturn(subscription1());
        given(modelMapper.map(Mockito.any(), Mockito.any())).willReturn(subscription1DTO());

        mockMvc.perform(post("/subscriptions/")
                .contentType(MediaType.APPLICATION_JSON)
        		.content(Subscription1DTOJSon()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("description", is("Desc 1")))
                .andExpect(jsonPath("active", is(Boolean.TRUE)));
    }
    
    @Test
    public void saveSubscriptionInternalServerError() throws Exception {
        given(subscriptionService.create(Mockito.any())).willReturn(Optional.empty());

        mockMvc.perform(post("/subscriptions/")
                .contentType(MediaType.APPLICATION_JSON)
        		.content(Subscription1DTOJSon()))
                .andExpect(status().isInternalServerError());
    }
    
    @Test
    public void deleteSubscription() throws Exception {
        mockMvc.perform(delete("/subscriptions/1")
                .contentType(MediaType.APPLICATION_JSON)
        		.content(Subscription1DTOJSon()))
                .andExpect(status().isOk());
    }
    
    @Test
    public void deleteSubscriptionWithoutIdShouldIsMethodNotAllowed() throws Exception {
        mockMvc.perform(delete("/subscriptions/")
                .contentType(MediaType.APPLICATION_JSON)
        		.content(Subscription1DTOJSon()))
                .andExpect(status().isMethodNotAllowed());
    }
    
    private Optional<Subscription> subscription1() {
    	Subscription subscription = new Subscription();
    	subscription.setDescription("Desc 1");
    	subscription.setActive(Boolean.TRUE);
    	return Optional.of(subscription);
    }
    
    private SubscriptionDTO subscription1DTO() {
    	SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
    	subscriptionDTO.setDescription("Desc 1");
    	subscriptionDTO.setActive(Boolean.TRUE);
    	return subscriptionDTO;
    }

    private String Subscription1DTOJSon() {
    	return "{" + "\"description\"" + " : " +  "\"Desc 1\"" + ", "
    			+ "\"active\"" + " : " +  "\"true\"" + "}";
    }
}
