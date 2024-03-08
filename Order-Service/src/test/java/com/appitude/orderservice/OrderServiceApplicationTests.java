package com.appitude.orderservice;

import com.appitude.orderservice.DTO.OrderLineItemsDto;
import com.appitude.orderservice.DTO.OrderRequest;
import com.appitude.orderservice.Repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class OrderServiceApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private OrderRepository repository;


	@Container
	@ServiceConnection
	static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:16.2");

	@Test
	void placeOrderApiTest() throws Exception {
		OrderRequest orderRequest = getOrderRequest();

		String orderRequestString = objectMapper.writeValueAsString(orderRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
				.contentType(MediaType.APPLICATION_JSON)
				.content(orderRequestString)
		).andExpect(status().isCreated());

		Assertions.assertEquals(1,repository.findAll().size());



	}

	private OrderRequest getOrderRequest(){
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setOrderLineItemsDtoList(getOrderLineItemDto());

		return orderRequest;
	}

	private List<OrderLineItemsDto> getOrderLineItemDto() {
		OrderLineItemsDto o1 = new OrderLineItemsDto();
		OrderLineItemsDto o2 = new OrderLineItemsDto();
		OrderLineItemsDto o3 = new OrderLineItemsDto();

		o1.setSkuCode(UUID.randomUUID().toString());
		o1.setPrice(BigDecimal.valueOf(12000));
		o1.setQuantity(1);
		o2.setSkuCode(UUID.randomUUID().toString());
		o2.setPrice(BigDecimal.valueOf(1200));
		o2.setQuantity(3);
		o3.setSkuCode(UUID.randomUUID().toString());
		o3.setPrice(BigDecimal.valueOf(1000));
		o3.setQuantity(2);

		List<OrderLineItemsDto> orderLineItemsDtoList = new ArrayList<>();
		orderLineItemsDtoList.add(o1);
		orderLineItemsDtoList.add(o2);
		orderLineItemsDtoList.add(o3);

		return orderLineItemsDtoList;

	}

}
