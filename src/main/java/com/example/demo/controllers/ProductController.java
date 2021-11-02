package com.example.demo.controllers;

import com.example.demo.dtos.ProductRequest;
import com.example.demo.dtos.ProductResponse;
import com.example.demo.helper.ResponseStatus;
import com.example.demo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;
    /*
    @PostMapping
    public ProductResponse addProduct(@RequestBody ProductRequest requestProduct){
        return productService.postNewProduct(requestProduct);
    }
    @PutMapping(value = "/{"+"id"+"}")
    public ProductResponse updateProduct(@PathVariable(value = "id") Long id, @RequestBody ProductRequest requestProduct){
        return productService.putNewProduct(id,requestProduct);
    }
    @GetMapping
    public List<ProductResponse> listAllData(){
        return productService.listAllProducts();
    }
    @DeleteMapping(value = "/{"+"id"+"}")
    public String deleteProducts (@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return "Delete Successfully";
    } */

    @PostMapping
    public ResponseEntity<ResponseStatus<ProductResponse>> post(@Valid @RequestBody ProductRequest productRequest, Errors errors){
        ResponseStatus<ProductResponse> responseProduct = new ResponseStatus<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseProduct.getMessages().add(error.getDefaultMessage());
            }
            responseProduct.setStatus(false);
            responseProduct.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseProduct);

        }
        responseProduct.setStatus(true);
        responseProduct.setData(productService.postNewProduct(productRequest));
        return ResponseEntity.ok(responseProduct);
    }
    @PutMapping(value = "/{"+"id"+"}")
    public ResponseEntity<ResponseStatus<ProductResponse>> put(@Valid @PathVariable(value = "id") Long id,
                                                               @RequestBody ProductRequest productRequest, Errors errors){
        ResponseStatus<ProductResponse> responseProduct = new ResponseStatus<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseProduct.getMessages().add(error.getDefaultMessage());
            }
            responseProduct.setStatus(false);
            responseProduct.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseProduct);

        }
        responseProduct.setStatus(true);
        responseProduct.setData(productService.putNewProduct(id,productRequest));

        return ResponseEntity.ok(responseProduct);

    }

    @GetMapping
    public List<ProductResponse> listAllData(){
        return productService.listAllProducts();
    }

    @DeleteMapping(value = "/{"+"id"+"}")
    public String deleteProduct(@PathVariable(value = "id") Long id){
        productService.deleteProduct(id);
        return "Delete Successfully";
    }

}
