/*
package com.engineer.lrogozinski.bootstrap;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.dto.AccountDto;
import com.engineer.lrogozinski.repositories.RoleRepository;
import com.engineer.lrogozinski.services.AccountService;
import com.engineer.lrogozinski.services.security.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.engineer.lrogozinski.domain.Role;

@Component
public class BootstrapClass implements ApplicationListener<ContextRefreshedEvent> {

    private final AccountService accountService;

    private final UserService userService;

    private final RoleRepository roleRepository;


    public BootstrapClass(AccountService accountService, UserService userService, RoleRepository roleRepository) {
        this.accountService = accountService;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadUsers();
        loadRoles();
        assignUser1ToAdmin();
    }

    public void assignUser1ToAdmin(){
        Account user1 = accountService.findByUsername("user1");
        Role admin = roleRepository.findByRole("ADMIN");
        Role user = roleRepository.findByRole("USER");
        user1.addRole(admin);
        user1.addRole(user);
        accountService.save(user1);
    }

    public void loadRoles(){
        Role admin = new Role();
        admin.setRole("ADMIN");
        roleRepository.save(admin);

        Role user = new Role();
        user.setRole("USER");
        roleRepository.save(user);
    }


    public void loadUsers(){
        AccountDto user1 = new AccountDto();
        user1.setUsername("user1");
        user1.setPassword("user1");
        userService.save(user1);

        AccountDto user2 =new AccountDto();
        user2.setUsername("user2");
        user2.setPassword("user2");
        userService.save(user2);
    }

}
*/
