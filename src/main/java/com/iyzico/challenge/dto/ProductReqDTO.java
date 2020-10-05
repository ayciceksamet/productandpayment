package com.iyzico.challenge.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

@Validated
public class ProductReqDTO {

    @NotEmpty(message = "Product Name must be filled ! ")
    @NotNull
    private String productName;

    private String productDescription;

    @Min(value=0,message = "The stock of Product could not be negative value ")
    @NotNull
    private Integer stockOfProduct;

    @Min(value=0,message = "The price of Product could not be negative value ")
    @NotNull
    private BigDecimal priceOfProduct;

    public ProductReqDTO() {
    }

    public ProductReqDTO(String productName, String productDescription, Integer stockOfProduct, BigDecimal priceOfProduct) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.stockOfProduct = stockOfProduct;
        this.priceOfProduct = priceOfProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getStockOfProduct() {
        return stockOfProduct;
    }

    public void setStockOfProduct(Integer stockOfProduct) {
        this.stockOfProduct = stockOfProduct;
    }

    public BigDecimal getPriceOfProduct() {
        return priceOfProduct;
    }

    public void setPriceOfProduct(BigDecimal priceOfProduct) {
        this.priceOfProduct = priceOfProduct;
    }

}
