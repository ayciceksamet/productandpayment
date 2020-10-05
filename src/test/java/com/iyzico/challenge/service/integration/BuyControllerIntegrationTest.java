package com.iyzico.challenge.service.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iyzico.challenge.controller.BuyController;
import com.iyzico.challenge.controller.ProductController;
import com.iyzico.challenge.dto.BuyReqDTO;
import com.iyzico.challenge.dto.ProductReqDTO;
import com.iyzico.challenge.dto.ProductRespDTO;
import com.iyzico.challenge.repository.ProductRepository;
import com.iyzico.challenge.service.BuyService;
import com.iyzico.challenge.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableAsync
public class BuyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductController productController;

    @Autowired
    private ProductService productService;

    @Autowired
    private BuyController buyController;

    @Autowired
    private BuyService buyService;

    @Autowired
    private ProductRepository productRepository;

    private static String asJsonString(final BuyReqDTO obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void productShouldBeSoldOnSystemAndIsOK() throws Exception {
        ProductReqDTO product = new ProductReqDTO("Swatch", "watch",20, BigDecimal.valueOf(150.00));
        ProductRespDTO productRespDTO = productService.addProduct(product);
        BuyReqDTO buyReqDTO = new BuyReqDTO(productRespDTO.getId(), 15);

        mockMvc.perform(post("/buy")
                .contentType("application/json")
                .content(asJsonString(buyReqDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void productShouldNotBeSoldOnSystemAndIsNOK() throws Exception {
        ProductReqDTO product = new ProductReqDTO("Swatch", "watch",20, BigDecimal.valueOf(150.00));
        ProductRespDTO productRespDTO = productService.addProduct(product);
        BuyReqDTO buyReqDTO = new BuyReqDTO(productRespDTO.getId(), 35);

        mockMvc.perform(post("/buy")
                .contentType("application/json")
                .content(asJsonString(buyReqDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }


}
