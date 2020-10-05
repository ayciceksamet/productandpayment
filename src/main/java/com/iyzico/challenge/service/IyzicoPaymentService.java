package com.iyzico.challenge.service;

import com.iyzico.challenge.configuration.DatabaseConfiguration;
import com.iyzico.challenge.entity.Payment;
import com.iyzico.challenge.repository.PaymentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
@EnableTransactionManagement
public class IyzicoPaymentService {

    private Logger logger = LoggerFactory.getLogger(IyzicoPaymentService.class);

    private BankService bankService;
    private PaymentRepository paymentRepository;


    @Autowired
    private EntityManager entityManager;

    private SessionFactory getSessionFac() {
        return entityManager.unwrap(Session.class).getSessionFactory();
    }

    public IyzicoPaymentService(BankService bankService, PaymentRepository paymentRepository ) {
        this.bankService = bankService;
        this.paymentRepository = paymentRepository;
    }


    public BankPaymentRequest priceOperation(BigDecimal price) {
        BankPaymentRequest request = new BankPaymentRequest();
        request.setPrice(price);
        return request;
    }

    public Payment paymentOperation(BankPaymentRequest request){
        BankPaymentResponse response = bankService.pay(request);
        Payment payment = new Payment();
        payment.setBankResponse(response.getResultCode());
        payment.setPrice(request.getPrice());
        return payment;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
        logger.info("Payment completed successfully ! ");
    }


    public void pay(BigDecimal price)   {
        BankPaymentRequest request = priceOperation(price);
        Payment paymentResponse =  paymentOperation(request);
        savePayment(paymentResponse);

    }
}
