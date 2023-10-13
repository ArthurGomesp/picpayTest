package com.picpay.Services;

import com.picpay.dtos.trasactioDataDTO;
import com.picpay.domain.transaction.Transaction;
import com.picpay.repositories.transactionRepository;
import com.picpay.domain.user.User;
import com.picpay.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class transactionService {

    @Autowired
    private transactionRepository transactionRepository;
    @Autowired
    private userServices userServices;
    @Autowired
    private userRepository userRepository;
    @Autowired
    private  notificationService notificationService;



    public void trasaction(trasactioDataDTO transactionData) throws Exception {

        User sender = userRepository.findByDocument(transactionData.documentSender()).orElseThrow(() -> new Exception("Usuário remetente não encontrado"));
        User reciver = userRepository.findByDocument(transactionData.documentReciver()).orElseThrow(() -> new Exception("Usuário destinatario não encontrado"));
        BigDecimal amount = transactionData.amount();
        if (!userServices.validateTransaction(sender, transactionData.amount())) {
            throw new Exception("Transação não autorizada");
        }
        Transaction transaction = new Transaction(transactionData.amount(), sender, reciver);

        sender.setBalance(sender.getBalance().subtract(amount));
        reciver.setBalance(reciver.getBalance().add(amount));
        this.userRepository.save(sender);
        this.userRepository.save(reciver);
        notificationService.sendNotification(sender, "trasaçao de R$" + amount + " aprovada");
        this.transactionRepository.save(transaction);
    }

}
