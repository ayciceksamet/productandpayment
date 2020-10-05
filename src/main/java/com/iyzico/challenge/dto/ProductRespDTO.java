package com.iyzico.challenge.dto;

import java.math.BigDecimal;

public class ProductRespDTO {

    private Long id;

    private String productName;

    private String productDescription;

    private Integer stockOfProduct;

    private BigDecimal priceOfProduct;

    public ProductRespDTO() {
    }

    public ProductRespDTO(String productName, String productDescription, Integer stockOfProduct, BigDecimal priceOfProduct) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.stockOfProduct = stockOfProduct;
        this.priceOfProduct = priceOfProduct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
