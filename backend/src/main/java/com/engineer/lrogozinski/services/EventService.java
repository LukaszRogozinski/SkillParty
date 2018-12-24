package com.engineer.lrogozinski.services;

import com.engineer.lrogozinski.domain.Event;
import com.engineer.lrogozinski.dto.EventDto;
import com.engineer.lrogozinski.exceptions.ServiceException;

import java.util.List;

public interface EventService{
    List<Event> findAll();

    Event findById(Integer id) throws ServiceException;

    Event save(Event object);

    void delete(Event object);

    void deleteById(Integer id);
}
