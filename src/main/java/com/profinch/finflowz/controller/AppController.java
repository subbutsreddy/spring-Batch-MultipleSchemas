package com.profinch.finflowz.controller;

import com.profinch.finflowz.account.repository.AccountRepository;
import com.profinch.finflowz.model.account.Account;
import com.profinch.finflowz.model.user.User;
import com.profinch.finflowz.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class AppController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void addData2DB() {
        userRepository.saveAll(Stream.of(new User(744, "subbu"), new User(455, "prahlad")).collect(Collectors.toList()));
        accountRepository.saveAll(
                Stream.of(new Account(111, "Microservices","sbin000123"), new Account(222, "Spring Boot","sbin00123")).collect(Collectors.toList()));
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/getAccounts")
    public List<Account> getAccount() {
        return accountRepository.findAll();
    }
}
