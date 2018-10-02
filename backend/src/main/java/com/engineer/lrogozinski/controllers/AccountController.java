package com.engineer.lrogozinski.controllers;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.services.AccountService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ComponentScan
@CrossOrigin
@RequestMapping(path = "/skillparty/api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/add")
    public @ResponseBody
    String addNewAccessLevel(@RequestParam String username, @RequestParam String password){
      Account account = new Account();
      account.setUsername(username);
      account.setPassword(password);
      accountService.save(account);
      return "added";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Account> getAllAccounts(){

        return accountService.findAll();
    }
}
