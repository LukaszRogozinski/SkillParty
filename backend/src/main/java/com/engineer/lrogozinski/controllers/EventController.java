package com.engineer.lrogozinski.controllers;

import com.engineer.lrogozinski.config.JwtTokenUtil;
import com.engineer.lrogozinski.domain.Event;
import com.engineer.lrogozinski.domain.EventCategory;
import com.engineer.lrogozinski.domain.UserData;
import com.engineer.lrogozinski.dto.EventDto;
import com.engineer.lrogozinski.dto.converter.EventDtoToEvent;
import com.engineer.lrogozinski.services.AccountService;
import com.engineer.lrogozinski.services.EventService;
import com.engineer.lrogozinski.services.UserDataService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/event")
public class EventController {

    private final JwtTokenUtil jwtTokenUtil;

    private final EventService eventService;

    private final AccountService accountService;

    private final EventDtoToEvent eventDtoToEvent;

    private final UserDataService userDataService;

    public EventController(JwtTokenUtil jwtTokenUtil, EventService eventService, AccountService accountService, EventDtoToEvent eventDtoToEvent, UserDataService userDataService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.eventService = eventService;
        this.accountService = accountService;
        this.eventDtoToEvent = eventDtoToEvent;
        this.userDataService = userDataService;
    }

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public List<Event> getAllEvents(){
        return eventService.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Transactional
    public void addEvent(@RequestBody EventDto eventDto){
       eventService.save(eventDtoToEvent.convert(eventDto));
       UserData userData =  userDataService.findByUsername(jwtTokenUtil.getUsernameFromToken(eventDto.getToken()));
       userData.addEvent(eventDtoToEvent.convert(eventDto));
       userDataService.save(userData);
       getEventList();
       //eventService.save(eventDtoToEvent.convert(eventDto));
    }



    private void getEventList() {
        UserData userData = userDataService.findByUsername("user1");
        List<Event> eventList = userData.getEventList();
        List<EventCategory> categories =  eventList.get(0).getEventCategories();
        int z = 5;
    }
}
