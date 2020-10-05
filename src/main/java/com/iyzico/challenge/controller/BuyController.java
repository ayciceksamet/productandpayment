package com.iyzico.challenge.controller;

import com.iyzico.challenge.dto.BuyReqDTO;
import com.iyzico.challenge.dto.BuyRespDTO;
import com.iyzico.challenge.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@RestController
public class BuyController {

    private final BuyService buyService;

    public BuyController(BuyService buyService) {
        this.buyService = buyService;
    }

    @PostMapping(value = "/buy")
    public BuyRespDTO buy(@Valid @RequestBody BuyReqDTO buyReqDTO) throws ExecutionException, InterruptedException {
        return buyService.buy(buyReqDTO);
    }

}
