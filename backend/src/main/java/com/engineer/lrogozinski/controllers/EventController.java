package com.engineer.lrogozinski.controllers;

import com.engineer.lrogozinski.domain.Event;
import com.engineer.lrogozinski.services.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/event")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(path = "/all")
    public @ResponseBody Iterable<Event> getAllEvents(){
        return eventService.findAll();
    }
}
