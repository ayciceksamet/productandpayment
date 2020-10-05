package com.iyzico.challenge.service;


import com.iyzico.challenge.dto.BuyReqDTO;
import com.iyzico.challenge.dto.BuyRespDTO;
import com.iyzico.challenge.dto.ProductReqDTO;
import com.iyzico.challenge.dto.ProductRespDTO;
import com.iyzico.challenge.exception.StockNotAvailableException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyServiceUnitTest {

    @Autowired
    BuyService buyService;

    @Autowired
    ProductService productService;

    @Test
    public void shouldProductCanBeSoldSuccessfully() throws Exception{
        ProductReqDTO product = new ProductReqDTO("Swatch", "watch",20, BigDecimal.valueOf(150.00));
        ProductRespDTO productRespDTO = productService.addProduct(product);
        BuyReqDTO buyReqDTO = new BuyReqDTO(productRespDTO.getId(), 15);
        BuyRespDTO buyRespDTO = buyService.buy(buyReqDTO);
        assertThat(buyRespDTO).isNotNull();
    }

    @Test(expected = StockNotAvailableException.class)
    public void shouldResourceNotFoundExceptionBeThrown() throws Exception{
        ProductReqDTO product = new ProductReqDTO("Swatch", "watch",20, BigDecimal.valueOf(150.00));
        ProductRespDTO productRespDTO = productService.addProduct(product);
        BuyReqDTO buyReqDTO = new BuyReqDTO(productRespDTO.getId(), 35);
        BuyRespDTO buyRespDTO = buyService.buy(buyReqDTO);
    }

    @Test(expected = StockNotAvailableException.class)
    public void shouldResourceNotFoundExceptionBeThrownForEmptyResource() throws Exception{
        ProductReqDTO product = new ProductReqDTO("Swatch", "watch",0, BigDecimal.valueOf(150.00));
        ProductRespDTO productRespDTO = productService.addProduct(product);
        BuyReqDTO buyReqDTO = new BuyReqDTO(productRespDTO.getId(), 1);
        BuyRespDTO buyRespDTO = buyService.buy(buyReqDTO);
    }



}
