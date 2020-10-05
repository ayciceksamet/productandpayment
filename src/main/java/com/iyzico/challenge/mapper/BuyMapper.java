package com.iyzico.challenge.mapper;

import com.iyzico.challenge.dto.BuyRespDTO;
import com.iyzico.challenge.dto.ProductRespDTO;
import com.iyzico.challenge.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class BuyMapper {

    public BuyRespDTO mapProductIDandResultToBuyRespDTO(Product product, String message) {
        BuyRespDTO buyRespDTO = new BuyRespDTO();
        buyRespDTO.setId(product.getId());
        buyRespDTO.setBuyResponse(message);
        return buyRespDTO;
    }
}
