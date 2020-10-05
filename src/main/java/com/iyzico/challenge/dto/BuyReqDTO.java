package com.iyzico.challenge.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BuyReqDTO {

    @NotNull
    private Long productId;

    @Min(value = 1, message = "You should buy at least one product !")
    private Integer count;

    public BuyReqDTO() { }

    public BuyReqDTO(Long productId, Integer count) {
        this.productId = productId;
        this.count = count;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
