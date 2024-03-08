package com.appitude.orderservice.Service;

import com.appitude.orderservice.DTO.OrderLineItemsDto;
import com.appitude.orderservice.DTO.OrderRequest;
import com.appitude.orderservice.Entity.Order;
import com.appitude.orderservice.Entity.OrderLineItems;
import com.appitude.orderservice.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;


    @Override
    public void placeOrder(OrderRequest orderRequest) {
        log.info(orderRequest.toString());
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());


        val orderLineItemList = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToOrderLineItem)
                        .toList();

        order.setOrderLineItemsList(orderLineItemList);
        orderRepository.save(order);

    }

    private OrderLineItems mapToOrderLineItem(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }
}
