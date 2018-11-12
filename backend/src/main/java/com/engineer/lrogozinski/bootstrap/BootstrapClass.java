package com.engineer.lrogozinski.bootstrap;

import com.engineer.lrogozinski.domain.*;
import com.engineer.lrogozinski.dto.AccountDto;
import com.engineer.lrogozinski.services.AccountService;
import com.engineer.lrogozinski.services.EventCategoryService;
import com.engineer.lrogozinski.services.RoleService;
import com.engineer.lrogozinski.services.UserDataService;
import com.engineer.lrogozinski.services.security.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile("bootstrap")
public class BootstrapClass implements ApplicationListener<ContextRefreshedEvent> {

    private final AccountService accountService;

    private final UserService userService;

    private final RoleService roleService;

    private final EventCategoryService eventCategoryService;

    private final UserDataService userDataService;


    public BootstrapClass(AccountService accountService, UserService userService, RoleService roleService, EventCategoryService eventCategoryService, UserDataService userDataService) {
        this.accountService = accountService;
        this.userService = userService;
        this.roleService = roleService;
        this.eventCategoryService = eventCategoryService;

        this.userDataService = userDataService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadUsers();
        loadRoles();
        assignUser1ToAdmin();
        loadEventCategories();
    }

    private void loadEventCategories() {

        EventCategory sport = new EventCategory();
        sport.setName("SPORT");
        sport.setImageUrl("https://banner2.kisspng.com/20180408/cdw/kisspng-computer-icons-sport-icon-design-spor-5acacc0bd07735.2046578415232399478539.jpg");
        eventCategoryService.save(sport);
        EventCategory relax = new EventCategory();
        relax.setName("RELAX");
        relax.setImageUrl("https://image.flaticon.com/icons/png/512/157/157830.png");
        eventCategoryService.save(relax);
    }

    public void assignUser1ToAdmin(){
        Account user1 = accountService.findByUsername("user1");
        Account user2 = accountService.findByUsername("user2");
        Account user3 = accountService.findByUsername("user3");
        Role admin = roleService.findByRole("ADMIN");
        Role user = roleService.findByRole("USER");
        user1.getRoles().add(admin);
        user1.getRoles().add(user);
        user2.getRoles().add(user);
        user3.getRoles().add(user);
        //user1.addRole(admin);
        //user1.addRole(user);
      //  user2.addRole(user);
        //user3.addRole(user);
       // accountService.save(user1);
       // accountService.save(user2);
       // accountService.save(user3);
    }

    public void loadRoles(){
        Role admin = new Role();
        admin.setRole("ADMIN");
        roleService.save(admin);

        Role user = new Role();
        user.setRole("USER");
        roleService.save(user);
    }


    public void loadUsers(){
        AccountDto user1 = new AccountDto();
        user1.setUsername("user1");
        user1.setPassword("user1");

        UserData userData1 = new UserData();
        userData1.setEmail("user1@email.com");
        userData1.setName("Jacek");
        userData1.setSurname("Wałbrzych");
        userData1.setCity("Zgierz");
        userData1.setStreet("Skierniewicka");
        userData1.setHouseNo(10);
        userData1.setFlatNo(2);
       // userData1.setAverageVote(0.0);
        user1.setUserdata(userData1);
        userService.save(user1);
//-------------------------------------------------------------------------

        AccountDto user2 =new AccountDto();
        user2.setUsername("user2");
        user2.setPassword("user2");


        UserData userData2 = new UserData();
        userData2.setEmail("user2@email.com");
        userData2.setName("Maria");
        userData2.setSurname("Kowalska");
        userData2.setCity("Łódź");
        userData2.setStreet("Zielona");
        userData2.setHouseNo(20);
        userData2.setFlatNo(5);
       // userData2.setAverageVote(0.0);
        user2.setUserdata(userData2);
        userService.save(user2);

//-------------------------------------------------------------------------

        AccountDto user3 =new AccountDto();
        user3.setUsername("user3");
        user3.setPassword("user3");


        UserData userData3 = new UserData();
        userData3.setEmail("user3@email.com");
        userData3.setName("Gienek");
        userData3.setSurname("Paździoch");
        userData3.setCity("Koszalin");
        userData3.setStreet("Malinowa");
        userData3.setHouseNo(30);
        userData3.setFlatNo(10);
      //  userData3.setAverageVote(0.0);
        user3.setUserdata(userData3);
        userService.save(user3);
    }
}
