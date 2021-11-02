package com.example.demo.service.impl;

import com.example.demo.dtos.ProductRequest;
import com.example.demo.dtos.ProductResponse;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepo;
import com.example.demo.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;

    private Product transferModelToRequest(ProductRequest productRequest){
        return Product.builder()
                .dateInput(productRequest.getDateInput())
                .name(productRequest.getName())
                .merek(productRequest.getMerek())
                .harga(productRequest.getHarga())
                .jumlah(productRequest.getJumlah())
                .build();
    }

    private ProductResponse transferResponseToModel(Product productRequest){
        return ProductResponse.builder()
                .id(productRequest.getId())
                .name(productRequest.getName())
                .merek(productRequest.getMerek())
                .harga(productRequest.getHarga())
                .jumlah(productRequest.getJumlah())
                .dateInput(productRequest.getDateInput())
                .build();
    }

    @Transactional
    @Override
    public ProductResponse postNewProduct(ProductRequest productRequest) {
        log.info("Post New Product: {}", productRequest.getName());
        Product product = Product.builder()
                .dateInput(productRequest.getDateInput())
                .name(productRequest.getName())
                .merek(productRequest.getMerek())
                .jumlah(productRequest.getJumlah())
                .harga(productRequest.getHarga())
                .build();
        Product responseProduct = transferModelToRequest(productRequest);
        Product saveProduct = productRepo.save(responseProduct);

        return transferResponseToModel(saveProduct);
    }

    @Transactional
    @SneakyThrows
    @Override
    public ProductResponse putNewProduct(Long id, ProductRequest productRequest) {
        Optional<Product> findIdProduct = productRepo.findById(id);
        if(findIdProduct.isEmpty()){
            throw new Exception("Product not found");
        }

        findIdProduct.get().setName(productRequest.getName());
        findIdProduct.get().setMerek(productRequest.getMerek());
        findIdProduct.get().setJumlah(productRequest.getJumlah());
        findIdProduct.get().setHarga(productRequest.getHarga());

        Product savedResponse = transferModelToRequest(productRequest);

        return transferResponseToModel(savedResponse);

    }

    @Override
    public List<ProductResponse> listAllProducts() {
        List<Product>cariSemuaProduct = productRepo.findAll();
        List<ProductResponse> tampilkanProduct = new ArrayList<>();

        cariSemuaProduct.forEach(search ->{
            ProductResponse returnProductResponse = transferResponseToModel(search);

            tampilkanProduct.add(returnProductResponse);
        });
        return tampilkanProduct;
    }
    @SneakyThrows
    @Override
    public void deleteProduct(Long id) {
        Optional<Product> findByData = productRepo.findById(id);
        if(findByData.isEmpty()){
            throw new Exception("ID Not Foubd");
        }
        productRepo.deleteById(id);

    }
}
