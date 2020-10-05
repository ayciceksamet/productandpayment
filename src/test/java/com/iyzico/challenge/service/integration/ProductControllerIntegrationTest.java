package com.iyzico.challenge.service.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iyzico.challenge.controller.ProductController;
import com.iyzico.challenge.dto.ProductReqDTO;
import com.iyzico.challenge.dto.ProductRespDTO;
import com.iyzico.challenge.entity.Product;
import com.iyzico.challenge.repository.ProductRepository;
import com.iyzico.challenge.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableAsync
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductController productController;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;


    private static String asJsonString(final ProductReqDTO obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void productShouldBeAddedToSystemAndIsOK() throws Exception {
        ProductReqDTO product = new ProductReqDTO("Swatch", "watch", 20, BigDecimal.valueOf(150.00));

        mockMvc.perform(post("/product/add")
                .contentType("application/json")
                .content(asJsonString(product))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void productShouldBeUpdatedOnSystemAndIsOK() throws Exception {
        ProductReqDTO product = new ProductReqDTO("Swatch", "watch", 20, BigDecimal.valueOf(150.00));
        ProductRespDTO productRespDTO = productService.addProduct(product);
        ProductReqDTO productUpd = new ProductReqDTO("Swatch", "watch", 20, BigDecimal.valueOf(350.00));

        mockMvc.perform(MockMvcRequestBuilders.
                 put("/product/update/" + productRespDTO.getId())
                .contentType("application/json")
                .content(asJsonString(productUpd))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void productShouldNotUpdatedOnSystemAndIsNOK() throws Exception {
        ProductReqDTO product = new ProductReqDTO("Swatch", "watch", 20, BigDecimal.valueOf(150.00));
        ProductRespDTO productRespDTO = productService.addProduct(product);
        ProductReqDTO productUpd = new ProductReqDTO("Swatch", "watch", 20, BigDecimal.valueOf(350.00));
        mockMvc.perform(MockMvcRequestBuilders.
                put("/product/update/" + -1L)
                .contentType("application/json")
                .content(asJsonString(productUpd))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void productShouldDeletedOnSystemAndIsOK() throws Exception {
        ProductReqDTO product = new ProductReqDTO("Swatch", "watch", 20, BigDecimal.valueOf(150.00));
        ProductRespDTO productRespDTO = productService.addProduct(product);
        mockMvc.perform(MockMvcRequestBuilders.
                 delete("/product/remove/" + productRespDTO.getId())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void productShouldFethcedOnSystemAndIsOK() throws Exception {
        ProductReqDTO product = new ProductReqDTO("Swatch", "watch", 20, BigDecimal.valueOf(150.00));
        ProductRespDTO productRespDTO = productService.addProduct(product);
        mockMvc.perform(MockMvcRequestBuilders.
                get("/product/get/" + productRespDTO.getId())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
