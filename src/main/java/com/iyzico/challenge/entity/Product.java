package com.iyzico.challenge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    @NotEmpty(message = "Product Name must be filled ! ")
    @NotNull
    private String productName;

    @Column(nullable = false)
    private String productDescription;

    @Column(nullable = false)
    @Min(value=0,message = "The stock of Product could not be negative value ")
    @NotNull
    private Integer stockOfProduct;

    @Column(nullable = false)
    @Min(value=0,message = "The price of Product could not be negative value ")
    @NotNull
    private BigDecimal priceOfProduct;

    public Product() {
    }

    public Product(String productName, String productDescription, int stockOfProduct, BigDecimal priceOfProduct) {
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
