package com.iyzico.challenge.service;

import com.iyzico.challenge.dto.ProductReqDTO;
import com.iyzico.challenge.dto.ProductRespDTO;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@Valid
public interface ProductService {

    public ProductRespDTO addProduct(ProductReqDTO productReqDTO);

    public ProductRespDTO updateProduct(Long id, ProductReqDTO productReqDTO);

    public ProductRespDTO removeProduct(Long id);

    public ProductRespDTO findProductById(Long id);

    public List<ProductRespDTO> findAllProducts();

}
