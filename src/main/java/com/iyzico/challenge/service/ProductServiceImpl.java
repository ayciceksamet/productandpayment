package com.iyzico.challenge.service;

import com.iyzico.challenge.dto.ProductReqDTO;
import com.iyzico.challenge.dto.ProductRespDTO;
import com.iyzico.challenge.entity.Product;
import com.iyzico.challenge.exception.ResourceNotFoundException;
import com.iyzico.challenge.mapper.ProductMapper;
import com.iyzico.challenge.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Valid
public class ProductServiceImpl implements ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductRespDTO addProduct(ProductReqDTO productReqDTO) {
        Product product = productMapper.mapProductReqDTOtoProduct(productReqDTO);
        productRepository.save(product);
        logger.info("Product saved successfully by id {} !", product.getId());
        return productMapper.mapProductToProductResDTO(product);
    }

    @Override
    public ProductRespDTO updateProduct(Long id, ProductReqDTO productReqDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString() + " product not found."));
        product = updateProduct(product , productReqDTO);
        product = productRepository.save(product);
        logger.info("Product updated successfully by id {} !", product.getId());
        return productMapper.mapProductToProductResDTO(product);
    }

    @Override
    public ProductRespDTO removeProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString() + " product not found."));
        productRepository.deleteById(product.getId());
        logger.info("Product deleted successfully by id {} !", product.getId());
        return productMapper.mapProductToProductResDTO(product);
    }

    @Override
    public ProductRespDTO findProductById(Long id) {
        return productRepository.findById(id)
                .map(product -> productMapper.mapProductToProductResDTO(product))
                .orElseThrow(() -> new ResourceNotFoundException(id.toString() + " product not found."));
    }

    @Override
    public List<ProductRespDTO> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .peek(product -> logger.info("Products are fetched successfully !") )
                .map(product -> productMapper.mapProductToProductResDTO(product))
                .collect(Collectors.toList());
    }

    private Product updateProduct(Product product, ProductReqDTO productReqDTO){
        product.setProductName(productReqDTO.getProductName());
        product.setProductDescription(productReqDTO.getProductDescription());
        product.setStockOfProduct(productReqDTO.getStockOfProduct());
        product.setPriceOfProduct(productReqDTO.getPriceOfProduct());
        return product;
    }

}
