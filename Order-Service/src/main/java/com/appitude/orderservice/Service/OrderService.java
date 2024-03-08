package com.appitude.orderservice.Service;

import com.appitude.orderservice.DTO.OrderRequest;

public interface OrderService {

    public void placeOrder(OrderRequest orderRequest);
}
