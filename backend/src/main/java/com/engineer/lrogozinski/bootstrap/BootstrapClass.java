package com.engineer.lrogozinski.bootstrap;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.repositories.RoleRepository;
import com.engineer.lrogozinski.services.AccountService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.engineer.lrogozinski.domain.Role;

@Component
public class BootstrapClass implements ApplicationListener<ContextRefreshedEvent> {

    private final AccountService accountService;

    private final RoleRepository roleRepository;


    public BootstrapClass(AccountService accountService, RoleRepository roleRepository) {
        this.accountService = accountService;
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
        user1.addRole(admin);
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
        Account user1 = new Account();
        user1.setUsername("user1");
        user1.setPassword("user1");
        accountService.save(user1);

        Account user2 =new Account();
        user2.setUsername("user2");
        user2.setPassword("user2");
        accountService.save(user2);
    }

}
