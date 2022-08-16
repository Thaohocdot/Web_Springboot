package com.nghien.api;

import com.nghien.entities.Account;
import com.nghien.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
    @Autowired
    AccountService accountService;

    @GetMapping
    public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
        if(admin.orElse(false)) {
            return accountService.getAdministrators();
        }
        return accountService.findAll();
    }

    @GetMapping("{username}")
    public Account getOne(@PathVariable("username") String username) {
        return accountService.findById(username);
    }

}
