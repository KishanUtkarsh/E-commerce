package com.appitude.orderservice.Service;

import com.appitude.orderservice.DTO.OrderRequest;

public interface OrderService {

    public String placeOrder(OrderRequest orderRequest);
}
