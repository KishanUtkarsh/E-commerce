package com.appitude.orderservice.DTO;


import com.appitude.orderservice.Entity.OrderLineItems;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequest {

    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
