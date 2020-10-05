package com.iyzico.challenge.service;


import com.iyzico.challenge.dto.BuyReqDTO;
import com.iyzico.challenge.dto.BuyRespDTO;
import com.iyzico.challenge.entity.Product;
import com.iyzico.challenge.exception.ResourceNotFoundException;
import com.iyzico.challenge.exception.StockNotAvailableException;
import com.iyzico.challenge.mapper.BuyMapper;
import com.iyzico.challenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

@Service
public class BuyServiceImpl implements BuyService {

    @Autowired
    private ProductService productService;

    @Autowired
    private IyzicoPaymentService iyzicoPaymentService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BuyMapper buyMapper;

    public synchronized BuyRespDTO buy(BuyReqDTO buyReqDTO){
        Product product = fetchToProduct(buyReqDTO);
        checkAvailabilityOfProduct(product,buyReqDTO);
        performBuyProcess(product, buyReqDTO);
        updateStockOfProduct(product, buyReqDTO.getCount());
        return buyMapper.mapProductIDandResultToBuyRespDTO(product,"Payment is performed successfully.");
    }

    private Product fetchToProduct(BuyReqDTO buyReqDTO) {
        return productRepository.findById(buyReqDTO.getProductId()).orElseThrow(() -> new ResourceNotFoundException(buyReqDTO.getProductId().toString() + " product not available. "));
    }

    private void performBuyProcess(Product product, BuyReqDTO buyReqDTO){
        BigDecimal priceOfProcess = product.getPriceOfProduct().multiply(BigDecimal.valueOf(buyReqDTO.getCount().intValue()));
        iyzicoPaymentService.pay(priceOfProcess);
    }

    private void checkAvailabilityOfProduct(Product product,BuyReqDTO buyReqDTO) {
        if ((!(product.getStockOfProduct() > 0) )|| (product.getStockOfProduct() - buyReqDTO.getCount() <= 0)) {
            throw new StockNotAvailableException(product.getId().toString());
        }
    }

    private void updateStockOfProduct(Product product, Integer countOfSale) {
        product.setStockOfProduct(product.getStockOfProduct() - countOfSale);
        productRepository.save(product);
    }

}
