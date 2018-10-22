package com.engineer.lrogozinski.controllers;

import com.engineer.lrogozinski.config.JwtTokenUtil;
import com.engineer.lrogozinski.domain.Event;
import com.engineer.lrogozinski.domain.UserData;
import com.engineer.lrogozinski.dto.EventDto;
import com.engineer.lrogozinski.dto.converter.EventDtoToEvent;
import com.engineer.lrogozinski.dto.converter.EventToEventDto;
import com.engineer.lrogozinski.services.AccountService;
import com.engineer.lrogozinski.services.EventService;
import com.engineer.lrogozinski.services.UserDataService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    private final EventToEventDto eventToEventDto;

    public EventController(JwtTokenUtil jwtTokenUtil, EventService eventService, AccountService accountService, EventDtoToEvent eventDtoToEvent, UserDataService userDataService, EventToEventDto eventToEventDto) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.eventService = eventService;
        this.accountService = accountService;
        this.eventDtoToEvent = eventDtoToEvent;
        this.userDataService = userDataService;
        this.eventToEventDto = eventToEventDto;
    }

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public List<EventDto> getAllEvents(){
        List<EventDto> eventDtoList = new ArrayList<>();
        eventService.findAll().forEach(event -> {
            eventDtoList.add(eventToEventDto.convert(event));
        });

        return eventDtoList;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Transactional
    public void addEvent(@RequestBody EventDto eventDto){
        Event event = eventDtoToEvent.convert(eventDto);
       eventService.save(event);
       UserData userData =  userDataService.findByUsername(jwtTokenUtil.getUsernameFromToken(eventDto.getToken()));
       userData.addEvent(event);
       userDataService.save(userData);
     //  getEventList();
       //eventService.save(eventDtoToEvent.convert(eventDto));
    }

/*    @RequestMapping(value="/allEventsLogedUser", method = RequestMethod.GET)
    public List<Event> getAllEventsLoggedUser(){
        UserData userData
    }*/
}
