package com.example.demo.service;

import com.example.demo.dtos.ProductRequest;
import com.example.demo.dtos.ProductResponse;
import com.example.demo.models.Product;

import java.util.List;

public interface ProductService {

    ProductResponse postNewProduct(ProductRequest productRequest);

    ProductResponse putNewProduct(Long id, ProductRequest productRequest);

    List<ProductResponse> listAllProducts();

    void deleteProduct(Long id);
}
