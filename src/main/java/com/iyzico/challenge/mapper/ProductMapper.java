package com.iyzico.challenge.mapper;

import com.iyzico.challenge.dto.ProductReqDTO;
import com.iyzico.challenge.dto.ProductRespDTO;
import com.iyzico.challenge.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product mapProductReqDTOtoProduct(ProductReqDTO productReqDTO) {
        Product product = new Product();
        product.setProductName(productReqDTO.getProductName());
        product.setProductDescription(productReqDTO.getProductDescription());
        product.setStockOfProduct(productReqDTO.getStockOfProduct());
        product.setPriceOfProduct(productReqDTO.getPriceOfProduct());
        return product;
    }

    public ProductRespDTO mapProductToProductResDTO(Product product) {
        ProductRespDTO productRes = new ProductRespDTO();
        productRes.setId(product.getId());
        productRes.setProductName(product.getProductName());
        productRes.setProductDescription(product.getProductDescription());
        productRes.setStockOfProduct(product.getStockOfProduct());
        productRes.setPriceOfProduct(product.getPriceOfProduct());
        return productRes;
    }

}
