package com.engineer.lrogozinski.controllers;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.services.AccountService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@ComponentScan
@CrossOrigin
@RequestMapping(path = "/account")
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

    @GetMapping(path = "/list")
    public String getAllAccounts(Model model){
        model.addAttribute("accounts",accountService.findAll());
        return "account/list";
    }

    @RequestMapping(path = "/show/{username}")
    public @ResponseBody Account getAccount(@PathVariable String username){
        return accountService.findByUsername(username);
    }
}
