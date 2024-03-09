package com.appitude.inventory.Service;

import com.appitude.inventory.DTO.InventoryResponse;

import java.util.List;

public interface InventoryService {
    public List<InventoryResponse> isInStock(List<String> skuCode);
}
