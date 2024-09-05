package br.com.washington.desafio_picpay_backend.services;

import br.com.washington.desafio_picpay_backend.entity.Transactions;
import br.com.washington.desafio_picpay_backend.entity.User;
import br.com.washington.desafio_picpay_backend.exceptions.ForbiddenException;
import br.com.washington.desafio_picpay_backend.exceptions.config.MessageException;
import br.com.washington.desafio_picpay_backend.mapper.ParseMapper;
import br.com.washington.desafio_picpay_backend.models.AccountType;
import br.com.washington.desafio_picpay_backend.models.MessageTransaction;
import br.com.washington.desafio_picpay_backend.models.UserVO;
import br.com.washington.desafio_picpay_backend.repositories.TransactionsRepository;
import br.com.washington.desafio_picpay_backend.services.external.ExternalVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

@Service
public class TransactionsService {

    @Autowired
    TransactionsRepository repository;

    @Autowired
    UserService service;

    @Autowired
    ExternalVerification externalVerification;


    public ResponseEntity<MessageTransaction> createTransaction(Transactions transactions) {
        externalVerification.authenticatedSever();


        var userSender = ParseMapper.parseObject(service.findByDocument(transactions.getUserSender()), User.class);
        var userReceive = ParseMapper.parseObject(service.findByDocument(transactions.getUserReceive()), User.class);

        isUserAllowedToSend(userSender);
        checkBalanceSender(userSender, transactions.getValue());


        userReceive.setBalance(userReceive.getBalance().add(transactions.getValue()));
        userSender.setBalance(userSender.getBalance().subtract(transactions.getValue()));

        var senderVO = ParseMapper.parseObject(userSender, UserVO.class);
        var receiveVO = ParseMapper.parseObject(userReceive, UserVO.class);

        var senderUpdated = service.updateBalance(senderVO);
        var receivedUpdated = service.updateBalance(receiveVO);

        transactions.setTimestamp(LocalDateTime.now());
        transactions.setUser(new HashSet<>(Arrays.asList(userSender, userReceive)));

        repository.save(transactions);

        String notificationMessage = null;
        try{
            notificationMessage = externalVerification.sendNotification(receiveVO).message();
        }catch (Exception _) {}
        Map<String, String> notificationMap = new HashMap<>();
        notificationMap.put("status", (notificationMessage == null) ? "Fails" : "Success");

        MessageTransaction messageTransaction = new MessageTransaction("Ok", senderUpdated, receivedUpdated, notificationMap);
        return ResponseEntity.ok().body(messageTransaction);
    }

    private void checkBalanceSender(User sender, BigDecimal amount){
        // The sender cannot send an amount greater than what he has.
        //For example: if he wants to send 20, and he only has 19.99, he cannot, because 19.99 - 20 = - 0.01, he cannot go negative.
        BigDecimal senderBigDecimal = sender.getBalance().subtract(amount);
        if(senderBigDecimal.compareTo(new BigDecimal(0)) < 0) throw new ForbiddenException(MessageException.INSUFFICIENT_BALANCE);
    }

    private void isUserAllowedToSend(User user){
        // Only users of type BUSINESS_USER can send money, these users can only receive money.
        if(user.getAccountType().toString().equalsIgnoreCase(AccountType.BUSINESS_USER.toString()))
            throw new ForbiddenException(MessageException.BUSINESS_NOT_ALLOW_TO_SEND_MONEY);
    }
}
