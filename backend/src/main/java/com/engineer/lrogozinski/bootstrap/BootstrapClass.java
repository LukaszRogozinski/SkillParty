package com.engineer.lrogozinski.bootstrap;

import com.engineer.lrogozinski.domain.*;
import com.engineer.lrogozinski.dto.AccountDto;
import com.engineer.lrogozinski.dto.EventDto;
import com.engineer.lrogozinski.dto.converter.EventDtoToEvent;
import com.engineer.lrogozinski.dto.converter.EventToEventDto;
import com.engineer.lrogozinski.services.*;
import com.engineer.lrogozinski.services.security.UserService;
import org.hibernate.Hibernate;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Profile("bootstrap")
public class BootstrapClass implements ApplicationListener<ContextRefreshedEvent> {

    private final AccountService accountService;

    private final UserService userService;

    private final RoleService roleService;

    private final EventCategoryService eventCategoryService;

    private final EventService eventService;

    private final UserDataService userDataService;

    private final EventToEventDto eventToEventDto;

    private final EventDtoToEvent eventDtoToEvent;

    public BootstrapClass( AccountService accountService, UserService userService, RoleService roleService, EventCategoryService eventCategoryService, EventService eventService, UserDataService userDataService, EventToEventDto eventToEventDto, EventDtoToEvent eventDtoToEvent) {
        this.accountService = accountService;
        this.userService = userService;
        this.roleService = roleService;
        this.eventCategoryService = eventCategoryService;
        this.eventService = eventService;
        this.userDataService = userDataService;
        this.eventToEventDto = eventToEventDto;
        this.eventDtoToEvent = eventDtoToEvent;
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
        eventCategoryService.save(sport);
        EventCategory relax = new EventCategory();
        relax.setName("RELAX");
        eventCategoryService.save(relax);
    }

    public void assignUser1ToAdmin(){
        Account user1 = accountService.findByUsername("user1");
        Account user2 = accountService.findByUsername("user2");
        Role admin = roleService.findByRole("ADMIN");
        Role user = roleService.findByRole("USER");
        user1.addRole(admin);
        user1.addRole(user);
        user2.addRole(user);
        accountService.save(user1);
        accountService.save(user2);
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
        userData1.setAverageVote(0.0);
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
        userData2.setAverageVote(0.0);
        user2.setUserdata(userData2);
        userService.save(user2);
    }
}
