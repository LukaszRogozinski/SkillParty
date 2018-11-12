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

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public List<EventDto> getAllEvents(){
        List<EventDto> eventDtoList = new ArrayList<>();
        eventService.findAll().forEach(event -> {
            eventDtoList.add(eventToEventDto.convert(event));
        });
        return eventDtoList;
    }

    @RequestMapping(value="/all-mine-events", method = RequestMethod.GET)
    public List<EventDto> getAllMineEvents(HttpServletRequest req) {
        String token = req.getHeader(HEADER_STRING).replace(TOKEN_PREFIX,"");
        UserData userData =  userDataService.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
        List<EventDto> eventDtoList = new ArrayList<>();
        userData.getEventList().forEach(event -> eventDtoList.add(eventToEventDto.convert(event)));
        return eventDtoList;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Transactional
    public void addEvent(@RequestBody EventDto eventDto, HttpServletRequest req){
        String token = req.getHeader(HEADER_STRING).replace(TOKEN_PREFIX,"");
        Event event = eventDtoToEvent.convert(eventDto);
       eventService.save(event);
       UserData userData =  userDataService.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
       userData.addEvent(event);
       userDataService.save(userData);
    }


    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public EventDto getEventDetails(@PathVariable(value = "id") Integer id, HttpServletRequest req){
        String token = req.getHeader(HEADER_STRING).replace(TOKEN_PREFIX,"");
        UserData userData =  userDataService.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
        EventDto eventDto =  eventToEventDto.convert(eventService.findById(id));
        if(userData.getId() == eventDto.getUsernameOwnerId()){
            eventDto.setIsEventLoggedUser(true);
        } else {
            eventDto.setIsEventLoggedUser(false);
        }
        return eventDto;
    }

    @RequestMapping(value = "/detail/{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> DeleteEvent(@PathVariable(value = "id") Integer id){
         eventService.deleteById(id);
         return ResponseEntity.ok().build();
    }

}
