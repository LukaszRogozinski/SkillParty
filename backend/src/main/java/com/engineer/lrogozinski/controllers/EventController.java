package com.engineer.lrogozinski.controllers;

import com.engineer.lrogozinski.config.JwtTokenUtil;
import com.engineer.lrogozinski.domain.Event;
import com.engineer.lrogozinski.domain.UserData;
import com.engineer.lrogozinski.dto.EventDto;
import com.engineer.lrogozinski.dto.converter.EventDtoToEvent;
import com.engineer.lrogozinski.dto.converter.EventToEventDto;
import com.engineer.lrogozinski.services.EventService;
import com.engineer.lrogozinski.services.UserDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.engineer.lrogozinski.security.Constants.HEADER_STRING;
import static com.engineer.lrogozinski.security.Constants.TOKEN_PREFIX;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/event")
public class EventController {

    private final JwtTokenUtil jwtTokenUtil;

    private final EventService eventService;

    private final EventDtoToEvent eventDtoToEvent;

    private final UserDataService userDataService;

    private final EventToEventDto eventToEventDto;

    public EventController(JwtTokenUtil jwtTokenUtil, EventService eventService, EventDtoToEvent eventDtoToEvent, UserDataService userDataService, EventToEventDto eventToEventDto) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.eventService = eventService;
        this.eventDtoToEvent = eventDtoToEvent;
        this.userDataService = userDataService;
        this.eventToEventDto = eventToEventDto;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/all", method = RequestMethod.GET)
    public List<EventDto> getAllEvents(){
        return eventService.findAll();
    }
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/all-mine-events", method = RequestMethod.GET)
    public List<EventDto> getAllMineEvents(HttpServletRequest req) {
        return eventService.getAllLoggedUserEvents(req);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Transactional
    public Event addEvent(@RequestBody EventDto eventDto, HttpServletRequest req){
       return eventService.addEvent(eventDto,req);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public EventDto getEventDetails(@PathVariable(value = "id") Integer id, HttpServletRequest req){
        return eventService.getEventDetails(id, req);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/detail/{id}/delete", method = RequestMethod.DELETE)
    public void DeleteEvent(@PathVariable(value = "id") Integer id){
         eventService.deleteById(id);
    }

}
