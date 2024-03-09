package com.appitude.orderservice.External.Service;


import com.appitude.orderservice.DTO.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryService {

    @GetMapping("/api/inventory")
    List<InventoryResponse> isInStock(@RequestParam("skuCode") List<String> skuCodes);
}
