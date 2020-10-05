package com.iyzico.challenge.service;

import com.iyzico.challenge.dto.BuyReqDTO;
import com.iyzico.challenge.dto.BuyRespDTO;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;


public interface BuyService {
    public BuyRespDTO buy(BuyReqDTO buyReqDTO);
}
