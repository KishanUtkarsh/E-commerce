package com.appitude.inventory;

import com.appitude.inventory.Entity.Inventory;
import com.appitude.inventory.Repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setQuantity(100);
			inventory.setSkuCode("iphone13");

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone14");
			inventory1.setQuantity(200);

			Inventory inventory2 = new Inventory();
			inventory2.setQuantity(0);
			inventory2.setSkuCode("iphone6");

			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory2);

		};
	}

}
