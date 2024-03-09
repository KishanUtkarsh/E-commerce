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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam("skuCode")List<String> skuCode){
        log.info("Fetching data from Database.");
        log.info(skuCode.toString());
        return service.isInStock(skuCode);
    }
}
