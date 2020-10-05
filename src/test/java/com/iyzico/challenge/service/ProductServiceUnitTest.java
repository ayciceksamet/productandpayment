package com.iyzico.challenge.service;

import com.iyzico.challenge.dto.ProductReqDTO;
import com.iyzico.challenge.dto.ProductRespDTO;
import com.iyzico.challenge.entity.Product;
import com.iyzico.challenge.exception.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceUnitTest {

    @Autowired
    ProductService productService;

    @Test
    public void shouldProductAddToSystemSuccessfully() throws Exception{
        ProductReqDTO product = new ProductReqDTO("Swatch", "watch",20, BigDecimal.valueOf(150.00));
        ProductRespDTO productRespDTO = productService.addProduct(product);
        assertThat(productRespDTO.getId()).isNotNull();
    }

    @Test
    public void shouldProductUpdatableOnSystemSuccessfully() throws Exception{
        ProductReqDTO product = new ProductReqDTO("Swatch", "watch",20, BigDecimal.valueOf(150.00));
        ProductRespDTO productRespDTO = productService.addProduct(product);
        ProductReqDTO productUpdated = new ProductReqDTO("Swatch", "watch",50, BigDecimal.valueOf(300.00));
        ProductRespDTO productRespDTUpdated = productService.updateProduct(productRespDTO.getId(),productUpdated );
        assertThat(productRespDTUpdated.getPriceOfProduct()).isEqualTo(BigDecimal.valueOf(300.00));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldProductDeletedOnSystemSuccessfully() throws Exception{
        ProductReqDTO product = new ProductReqDTO("Swatch", "watch",20, BigDecimal.valueOf(150.00));
        ProductRespDTO productRespDTO = productService.addProduct(product);
        productService.removeProduct(productRespDTO.getId());
        productService.findProductById(productRespDTO.getId());
    }

    @Test
    public void shouldProductFindOnSystemSuccessfully() throws Exception{
        ProductReqDTO product = new ProductReqDTO("Swatch", "watch",20, BigDecimal.valueOf(150.00));
        ProductRespDTO productRespDTO = productService.addProduct(product);
        assertThat(productService.findProductById(productRespDTO.getId()).getProductName()).isEqualTo(product.getProductName());
    }

    @Test
    public void shouldAllProductWillBeFoundOnSystemSuccessfully() throws Exception{
        ProductReqDTO product  = new ProductReqDTO("Swatch", "watch",20, BigDecimal.valueOf(150.00));
        ProductReqDTO product2 = new ProductReqDTO("Pulsar", "watch",20, BigDecimal.valueOf(350.00));
        ProductReqDTO product3 = new ProductReqDTO("Quartz", "watch",20, BigDecimal.valueOf(450.00));
        List<ProductReqDTO> productReqDTOList = new ArrayList<>();
        productReqDTOList.add(product);
        productReqDTOList.add(product2);
        productReqDTOList.add(product3);
        productService.addProduct(product);
        productService.addProduct(product2);
        productService.addProduct(product3);
        assertThat(!productService.findAllProducts().isEmpty());
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldThrowDataExceptionForNullProductName() throws Exception{
        ProductReqDTO productNullName  = new ProductReqDTO(null, "watch",20, BigDecimal.valueOf(150.00));
        productService.addProduct(productNullName);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldThrowDataExceptionForEmptyProductName() throws Exception{
        ProductReqDTO productEmptyName  = new ProductReqDTO("", "watch",20, BigDecimal.valueOf(150.00));
        productService.addProduct(productEmptyName);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldThrowDataExceptionForNegativePriceOfProduct() throws Exception{
        ProductReqDTO productNullName  = new ProductReqDTO("Swatch", "watch",20, BigDecimal.valueOf(-150.00));
        productService.addProduct(productNullName);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldThrowDataExceptionForNegativeStockOfProduct() throws Exception{
        ProductReqDTO productNullName  = new ProductReqDTO("", "watch",-20, BigDecimal.valueOf(150.00));
        productService.addProduct(productNullName);
    }



}
