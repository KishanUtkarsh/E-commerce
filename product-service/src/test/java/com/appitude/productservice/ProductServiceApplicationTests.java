package com.appitude.productservice;

import com.appitude.productservice.DTO.ProductRequest;
import com.appitude.productservice.Repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.math.BigDecimal;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired //jackson databind -> convert java pojo to json or json to java object
	private ObjectMapper objectMapper;

	@Autowired
	private ProductRepository repository;

	@Container
	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.25");

//	setting up container (Docker mongo db properties before spring 3.1.0)
//	@DynamicPropertySource
//	static void setRegistry(DynamicPropertyRegistry registry){
//		registry.add("spring.data.mongodb.uri" , mongoDBContainer::getReplicaSetUrl);
//	}


	@Test
	@DisplayName("This shows the product is created or not.")
	void shouldCreateProduct() throws Exception {

		ProductRequest productRequest = getProductRequest();
		String productRequestString = objectMapper.writeValueAsString(productRequest);


		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString)
		).andExpect(status().isCreated());
		Assertions.assertEquals(1,repository.findAll().size());


	}

	private ProductRequest getProductRequest(){
		return ProductRequest.builder()
				.name("Cargo Pant")
				.description("Jeans with the cargo style which has so many packets")
				.price(BigDecimal.valueOf(1699))
				.build();
	}

}
