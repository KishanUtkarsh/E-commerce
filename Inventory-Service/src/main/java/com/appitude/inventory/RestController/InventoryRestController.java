package com.appitude.inventory.RestController;

import com.appitude.inventory.DTO.InventoryResponse;
import com.appitude.inventory.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@Slf4j
@RequiredArgsConstructor
public class InventoryRestController {

    private final InventoryService service;

    //http://localhost:8082/api/inventory?skuCode=iphone13&skuCode=iphone6
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam("skuCode") List<String> skuCodes) {
        log.info("Fetching data from Database.");
        log.info(skuCodes.toString());
        return service.isInStock(skuCodes);
    }
}
