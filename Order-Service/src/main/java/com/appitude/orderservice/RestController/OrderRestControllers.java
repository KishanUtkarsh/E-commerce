package com.appitude.orderservice.RestController;

import com.appitude.orderservice.DTO.OrderRequest;
import com.appitude.orderservice.Service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@Slf4j
@RequiredArgsConstructor
public class OrderRestControllers {

    private final OrderService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        service.placeOrder(orderRequest);
        return "Order Placed Successful";
    }

}
