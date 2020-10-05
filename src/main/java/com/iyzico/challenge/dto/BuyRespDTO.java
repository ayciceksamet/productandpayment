package com.iyzico.challenge.dto;

public class BuyRespDTO {

    private Long id;

    private String buyResponse;

    public BuyRespDTO() { }

    public BuyRespDTO(Long id, String buyResponse) {
        this.id = id;
        this.buyResponse = buyResponse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyResponse() {
        return buyResponse;
    }

    public void setBuyResponse(String buyResponse) {
        this.buyResponse = buyResponse;
    }

}
