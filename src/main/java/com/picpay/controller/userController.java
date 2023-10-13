package com.picpay.controller;

import com.picpay.dtos.trasactioDataDTO;
import com.picpay.dtos.DataDetailsUser;
import com.picpay.dtos.userDataCreateDto;
import com.picpay.domain.user.User;
import com.picpay.repositories.userRepository;
import com.picpay.Services.transactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.picpay.Services.userServices;

@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    private userRepository repository;
    @Autowired
    private userServices userServices;
    @Autowired
    private transactionService transactionService;


    @PostMapping
    @Transactional
    @ResponseBody
    public ResponseEntity createUser(@RequestBody userDataCreateDto userData){
        return userServices.createUser(userData);
    }

    @GetMapping("/userDatail/{id}")
    @Transactional
    @ResponseBody
    public ResponseEntity userDatails(@PathVariable Long id){
        User user = repository.getById(id);
        return ResponseEntity.ok(new DataDetailsUser(user));
    }

    @PostMapping("/transaction")
    @ResponseBody
    @Transactional
    public void trasaction(@RequestBody trasactioDataDTO trasactioDataDTO) throws Exception {
        transactionService.trasaction(trasactioDataDTO);
    }
}
