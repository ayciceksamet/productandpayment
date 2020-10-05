package com.iyzico.challenge.controller;

import com.iyzico.challenge.dto.ProductReqDTO;
import com.iyzico.challenge.dto.ProductRespDTO;
import com.iyzico.challenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/add")
    public ProductRespDTO addProduct(@Valid @RequestBody(required = true) ProductReqDTO productReqDTO) {
        return productService.addProduct(productReqDTO);
    }

    @PutMapping(value = "/update/{id}")
    public ProductRespDTO updateProduct(@PathVariable Long id, @Valid @RequestBody(required = true) ProductReqDTO productReqDTO) {
        return productService.updateProduct(id, productReqDTO);
    }

    @DeleteMapping(value = "/remove/{id}")
    public ProductRespDTO removeProduct(@PathVariable Long id) {
        return productService.removeProduct(id);
    }

    @GetMapping(value = "/get/{id}")
    public ProductRespDTO getProduct(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @GetMapping(value = "/get")
    public List<ProductRespDTO> getProduct() {
        return productService.findAllProducts();
    }


}
