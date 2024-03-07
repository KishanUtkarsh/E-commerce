package com.appitude.productservice.Service;

import com.appitude.productservice.DTO.ProductRequest;
import com.appitude.productservice.DTO.ProductResponse;
import com.appitude.productservice.Entity.Product;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {
    public void createProduct(ProductRequest productRequest);
    public List<ProductResponse> getAllProducts();
}
