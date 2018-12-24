package com.engineer.lrogozinski.services.impl;

import com.engineer.lrogozinski.domain.Event;
import com.engineer.lrogozinski.dto.EventDto;
import com.engineer.lrogozinski.dto.converter.EventDtoToEvent;
import com.engineer.lrogozinski.dto.converter.EventToEventDto;
import com.engineer.lrogozinski.exceptions.ServiceException;
import com.engineer.lrogozinski.repositories.EventRepository;
import com.engineer.lrogozinski.services.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.engineer.lrogozinski.exceptions.ExceptionsMessage.CANNOT_FIND_EVENT_WITH_PROVIDED_ID;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    private EventDtoToEvent eventDtoToEvent;

    private EventToEventDto eventToEventDto;

    public EventServiceImpl(EventRepository eventRepository, EventDtoToEvent eventDtoToEvent, EventToEventDto eventToEventDto) {
        this.eventRepository = eventRepository;
        this.eventDtoToEvent = eventDtoToEvent;
        this.eventToEventDto = eventToEventDto;
    }

    @Override
    public List<Event> findAll() {
        List<Event> eventList = new ArrayList<>();
        eventRepository.findAll().forEach(eventList::add);
        return eventList;
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
}
