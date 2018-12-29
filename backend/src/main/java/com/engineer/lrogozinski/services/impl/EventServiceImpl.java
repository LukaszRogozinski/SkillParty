package com.engineer.lrogozinski.services.impl;

import com.engineer.lrogozinski.config.JwtTokenUtil;
import com.engineer.lrogozinski.domain.Event;
import com.engineer.lrogozinski.domain.UserData;
import com.engineer.lrogozinski.dto.EventDto;
import com.engineer.lrogozinski.dto.converter.EventDtoToEvent;
import com.engineer.lrogozinski.dto.converter.EventToEventDto;
import com.engineer.lrogozinski.exceptions.ServiceException;
import com.engineer.lrogozinski.repositories.EventRepository;
import com.engineer.lrogozinski.services.EventService;
import com.engineer.lrogozinski.services.UserDataService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.engineer.lrogozinski.exceptions.ExceptionsMessage.CANNOT_FIND_EVENT_WITH_PROVIDED_ID;
import static com.engineer.lrogozinski.security.Constants.HEADER_STRING;
import static com.engineer.lrogozinski.security.Constants.TOKEN_PREFIX;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    private final EventToEventDto eventToEventDto;

    private final UserDataService userDataService;

    private final JwtTokenUtil jwtTokenUtil;

    private final EventDtoToEvent eventDtoToEvent;

    public EventServiceImpl(EventRepository eventRepository, EventToEventDto eventToEventDto, UserDataService userDataService, JwtTokenUtil jwtTokenUtil, EventDtoToEvent eventDtoToEvent) {
        this.eventRepository = eventRepository;
        this.eventToEventDto = eventToEventDto;
        this.userDataService = userDataService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.eventDtoToEvent = eventDtoToEvent;
    }

    @Override
    public List<EventDto> findAll() {
        List<EventDto> eventDtoList = new ArrayList<>();
        eventRepository.findAll().forEach(event -> {
            eventDtoList.add(eventToEventDto.convert(event));
        });
        return eventDtoList;
    }

    @Override
    public Event findById(Integer id) {
        return eventRepository.findById(id).orElseThrow(() -> new ServiceException(CANNOT_FIND_EVENT_WITH_PROVIDED_ID));
    }

    @Override
    public Event save(Event object) {
        return eventRepository.save(object);
    }

    @Override
    public void delete(Event object) {
        eventRepository.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<EventDto> getAllLoggedUserEvents(HttpServletRequest req) {
        String token = req.getHeader(HEADER_STRING).replace(TOKEN_PREFIX,"");
        UserData userData =  userDataService.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
        List<EventDto> eventDtoList = new ArrayList<>();
        userData.getEventList().forEach(event -> eventDtoList.add(eventToEventDto.convert(event)));
        return eventDtoList;
    }

    @Override
    public Event addEvent(EventDto eventDto, HttpServletRequest req) {
        String token = req.getHeader(HEADER_STRING).replace(TOKEN_PREFIX,"");
        Event event = eventDtoToEvent.convert(eventDto);
        UserData userData =  userDataService.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
        userData.addEvent(event);
        return eventRepository.save(event);
    }

    @Override
    public EventDto getEventDetails(Integer id, HttpServletRequest req) {
        String token = req.getHeader(HEADER_STRING).replace(TOKEN_PREFIX,"");
        UserData userData =  userDataService.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
        EventDto eventDto =  eventToEventDto.convert(this.findById(id));
        if(userData.getId() == eventDto.getUsernameOwnerId()){
            eventDto.setIsEventLoggedUser(true);
        } else {
            eventDto.setIsEventLoggedUser(false);
        }
        return eventDto;
    }

}
