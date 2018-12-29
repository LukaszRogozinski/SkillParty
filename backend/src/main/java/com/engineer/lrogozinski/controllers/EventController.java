package com.engineer.lrogozinski.controllers;

import com.engineer.lrogozinski.domain.Event;
import com.engineer.lrogozinski.dto.EventDto;
import com.engineer.lrogozinski.services.EventService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController( EventService eventService) {
        this.eventService = eventService;
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
