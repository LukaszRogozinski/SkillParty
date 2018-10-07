package com.engineer.lrogozinski.rest;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.domain.Role;
import com.engineer.lrogozinski.dto.AccountDto;
import com.engineer.lrogozinski.dto.converter.AccountDtoToAccount;
import com.engineer.lrogozinski.services.AccountDtoService;
import com.engineer.lrogozinski.services.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/skillparty/api/users")
public class UserRest {

    private final AccountDtoService accountDtoService;
    private final AccountService accountService;

    private final AccountDtoToAccount accountDtoToAccount;

    public UserRest(AccountDtoService accountDtoService, AccountService accountService, AccountDtoToAccount accountDtoToAccount) {
        this.accountDtoService = accountDtoService;
        this.accountService = accountService;
        this.accountDtoToAccount = accountDtoToAccount;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Account> getAllUsers(){
        return accountService.findAll();
    }

    @GetMapping(path = "/allroles")
    public @ResponseBody Iterable<Role> getAll(){
        return accountService.findByUsername("user2").getRoles();
    }


    @PostMapping("sing-up")
    public void singUp(@RequestBody AccountDto accountDto){

        accountService.save(accountDtoToAccount.convert(accountDto));
    }

}
