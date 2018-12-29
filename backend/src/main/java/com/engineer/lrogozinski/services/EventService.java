package com.engineer.lrogozinski.services;

import com.engineer.lrogozinski.domain.Event;
import com.engineer.lrogozinski.dto.EventDto;
import com.engineer.lrogozinski.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EventService{

    List<EventDto> findAll();

    Event findById(Integer id) throws ServiceException;

    Event save(Event object);

    void delete(Event object);

    void deleteById(Integer id);

    List<EventDto> getAllLoggedUserEvents(HttpServletRequest req);

    Event addEvent(EventDto eventDto, HttpServletRequest req);

    EventDto getEventDetails(Integer id, HttpServletRequest req);

}
